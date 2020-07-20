package paulevs.corelib.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;

import paulevs.corelib.math.Vec2F;

public class TextureAtlas
{
	private static final BufferedImage EMPTY = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);

	static
	{
		int magenta = (255 << 24) | (255 << 16) | 255;
		int black = (255 << 24);

		EMPTY.setRGB(0, 0, magenta);
		EMPTY.setRGB(1, 1, magenta);
		EMPTY.setRGB(0, 1, black);
		EMPTY.setRGB(1, 0, black);
	}

	private final HashMap<String, UVPair> uvs = new HashMap<String, UVPair>();
	private final Texture2D atlas;
	private final Vec2F pixelSize;
	private final float size;

	public TextureAtlas(String primal, HashSet<String> textures)
	{
		List<ImageInfo> tiles = loadTiles(textures);
		tiles.add(new ImageInfo(EMPTY, "notile"));

		Collections.sort(tiles, (img1, img2) -> {
			int a = Math.max(img1.img.getWidth(), img1.img.getHeight());
			int b = Math.max(img2.img.getWidth(), img2.img.getHeight());
			return b - a;
		});
		if (primal != null) tiles.add(0, new ImageInfo(loadImage(primal), primal));

		List<Rect> rects = new ArrayList<Rect>();
		rects.add(new Rect(0, 0, 16384, 16384));

		Vec2F[] poses = new Vec2F[tiles.size()];
		for (int i = 0; i < tiles.size(); i++)
		{
			Rect possible = rects.get(0);
			ImageInfo info = tiles.get(i);
			for (Rect r : rects)
			{
				if (r.canFit(info.img.getWidth(), info.img.getHeight()))
				{
					possible = r;
					break;
				}
			}
			poses[i] = new Vec2F(possible.x, possible.y);
			possible.split(info.img.getWidth(), info.img.getHeight(), rects);
			Collections.sort(rects);
		}

		int sideX = 0;
		int sideY = 0;
		for (int i = 0; i < tiles.size(); i++)
		{
			ImageInfo info = tiles.get(i);
			sideX = Math.max((int) poses[i].getX() + info.img.getWidth(), sideX);
			sideY = Math.max((int) poses[i].getY() + info.img.getHeight(), sideY);
		}

		sideX = 1 << (int) Math.ceil(Math.log(sideX) / Math.log(2));
		sideY = 1 << (int) Math.ceil(Math.log(sideY) / Math.log(2));

		size = Math.max(sideX, sideY);

		pixelSize = new Vec2F(1F / size, 1F / size);

		BufferedImage atlasIMG = new BufferedImage((int) size, (int) size, BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < tiles.size(); i++)
		{
			ImageInfo info = tiles.get(i);
			drawImage(info.img, atlasIMG, (int) poses[i].getX(), (int) poses[i].getY());
			Vec2F start = new Vec2F(poses[i].getX() / size, poses[i].getY() / size);
			Vec2F end = new Vec2F((poses[i].getX() + info.img.getWidth()) / size,
					(poses[i].getY() + info.img.getHeight()) / size);
			uvs.put(info.name, new UVPair(start, end));
		}

		/*
		 * try { ImageIO.write(atlasIMG, "png", new
		 * File("D:/MC_BetterNether_1.15.1/Example-Mod-b1.7.3/atlas.png")); }
		 * catch (IOException e) { e.printStackTrace(); }
		 */

		atlas = new Texture2D(atlasIMG);
	}

	private void drawImage(BufferedImage img, BufferedImage buffer, int x, int y)
	{
		for (int px = 0; px < img.getWidth(); px++)
		{
			int bx = x + px;
			for (int py = 0; py < img.getHeight(); py++)
			{
				int by = y + py;
				buffer.setRGB(bx, by, img.getRGB(px, py));
			}
		}
	}

	public void bind()
	{
		atlas.bind();
	}

	public UVPair getUV(String texture)
	{
		UVPair uv = uvs.get(texture);
		return uv != null ? uv : uvs.get("notile");
	}

	private List<ImageInfo> loadTiles(HashSet<String> textures)
	{
		List<ImageInfo> result = new ArrayList<ImageInfo>();
		for (String name : textures)
		{
			BufferedImage img = loadImage(name);
			if (img != null) result.add(new ImageInfo(img, name));
		}
		return result;
	}

	private BufferedImage loadImage(String filename)
	{
		BufferedImage image = EMPTY;
		if (filename != null && !filename.isEmpty())
		{
			try
			{
				InputStream in = getClass().getResourceAsStream(filename);
				if (in != null)
				{
					image = ImageIO.read(in);
					in.close();
				}
			}
			catch (IOException e)
			{}
		}
		return image;
	}

	private class Rect implements Comparable<Rect>
	{
		int x;
		int y;
		int w;
		int h;

		Rect(int x, int y, int w, int h)
		{
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}

		public boolean isZero()
		{
			return w == 0 || h == 0;
		}

		public boolean canFit(int width, int height)
		{
			return width <= w && height <= h;
		}

		private List<Rect> splitX(int dx, int dy)
		{
			List<Rect> res = new ArrayList<Rect>();
			Rect a = new Rect(x, y + dy, dx, h - dy);
			Rect b = new Rect(x + dx, y, w - dx, h);
			if (!a.isZero()) res.add(a);
			if (!b.isZero()) res.add(b);
			return res;
		}

		private List<Rect> splitY(int dx, int dy)
		{
			List<Rect> res = new ArrayList<Rect>();
			Rect a = new Rect(x + dx, y, w - dx, dy);
			Rect b = new Rect(x, y + dy, w, h - dy);
			if (!a.isZero()) res.add(a);
			if (!b.isZero()) res.add(b);
			return res;
		}

		public void split(int width, int height, List<Rect> list)
		{
			list.remove(this);
			if (w - width > h - height)
			{
				List<Rect> rects = splitX(width, height);
				list.addAll(rects);
			}
			else
			{
				List<Rect> rects = splitY(width, height);
				list.addAll(rects);
			}
		}

		@Override
		public int compareTo(Rect r)
		{
			int a = Math.max(w, h);
			int b = Math.max(r.w, r.h);
			return a - b;
		}
	}

	private class ImageInfo
	{
		public BufferedImage img;
		public String name;

		public ImageInfo(BufferedImage img, String name)
		{
			this.img = img;
			this.name = name;
		}
	}

	public Texture2D getTexture()
	{
		return atlas;
	}

	public Vec2F getPixelSize()
	{
		return pixelSize;
	}

	public float getSize()
	{
		return size;
	}
}
