package paulevs.corelib.model;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import paulevs.corelib.LocationRandom;
import paulevs.corelib.model.shape.Shape;
import paulevs.corelib.texture.UVPair;

public abstract class Model
{
	protected static final LocationRandom RANDOM = new LocationRandom();

	private Map<String, TextureInfo> textures = Maps.newHashMap();

	public Model()
	{}

	public abstract void renderBlock();

	public abstract void renderItem();

	public abstract UVPair particleUV();

	public Model addTexture(String name, String texture)
	{
		textures.put(name, new TextureInfo(texture));
		return this;
	}

	public void init()
	{
		textures.forEach((name, info) -> {
			info.init();
		});
	}

	public boolean hasItem()
	{
		return true;
	}

	public boolean hasBlock()
	{
		return true;
	}

	protected void setTexture(String name)
	{
		Shape.setUV(textures.get(name).getUV());
	}

	protected UVPair getUV(String texture)
	{
		return textures.get(texture).getUV();
	}

	public List<String> getTextures()
	{
		List<String> list = Lists.newArrayList();
		textures.forEach((name, info) -> {
			list.add(info.getName());
		});
		return list;
	}
}
