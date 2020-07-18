package paulevs.corelib.model.shape;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.sortme.GameRenderer;
import net.minecraft.tile.Tile;
import paulevs.corelib.CoreLib;
import paulevs.corelib.math.Vec3F;

public class ShapeBoxCustom extends Shape {
	private static int field_83 = -1;
	private static boolean field_84 = false;
	private static boolean field_85 = false;
	private static boolean field_67 = true;
	private static int field_86 = 0;
	private static int field_87 = 0;
	private static int field_88 = 0;
	private static int field_89 = 0;
	private static int field_90 = 0;
	private static int field_91 = 0;
	private static boolean field_92;
	private static float field_93;
	private static float field_94;
	private static float field_95;
	private static float field_96;
	private static float field_97;
	private static float field_98;
	private static float field_99;
	private static float field_100;
	private static float field_101;
	private static float field_102;
	private static float field_103;
	private static float field_104;
	private static float field_105;
	private static float field_41;
	private static float field_42;
	private static float field_43;
	private static float field_44;
	private static float field_45;
	private static float field_46;
	private static float field_47;
	private static float field_48;
	private static float field_49;
	private static float field_50;
	private static float field_51;
	private static float field_52;
	private static float field_53;
	private static float field_54;
	private static int field_55 = 1;
	private static float field_56;
	private static float field_57;
	private static float field_58;
	private static float field_59;
	private static float field_60;
	private static float field_61;
	private static float field_62;
	private static float field_63;
	private static float field_64;
	private static float field_65;
	private static float field_66;
	private static float field_68;
	private static boolean field_69;
	private static boolean field_70;
	private static boolean field_71;
	private static boolean field_72;
	private static boolean field_73;
	private static boolean field_74;
	private static boolean field_75;
	private static boolean field_76;
	private static boolean field_77;
	private static boolean field_78;
	private static boolean field_79;
	private static boolean field_80;

	private float minX;
	private float maxX;
	private float minY;
	private float maxY;
	private float minZ;
	private float maxZ;
	
	private float size;
	
	private static final Vec3F POS_MIN = new Vec3F();
	private static final Vec3F POS_MAX = new Vec3F();

	public ShapeBoxCustom(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;

		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
	}

	public ShapeBoxCustom() {
		this(0, 0, 0, 1, 1, 1);
	}

	public void reshape(float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
		this.minX = minX;
		this.minY = minY;
		this.minZ = minZ;

		this.maxX = maxX;
		this.maxY = maxY;
		this.maxZ = maxZ;
	}

	@Override
	public void render() {
		POS_MIN.set(OFFSET).add(minX, minY, minZ);
		POS_MAX.set(OFFSET).add(maxX, maxY, maxZ);

		float red = (float) ((color >> 16) & 255) / 255F;
		float green = (float) ((color >> 8) & 255) / 255F;
		float blue = (float) (color & 255) / 255F;

		if (GameRenderer.field_2340) {
			float newRed = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
			float newGreen = (red * 30.0F + green * 70.0F) / 100.0F;
			float newBlue = (red * 30.0F + blue * 70.0F) / 100.0F;
			red = newRed;
			green = newGreen;
			blue = newBlue;
		}
		
		size = CoreLib.getAtlasSize();

		if (Minecraft.isSmoothLightingEnabled())
			smoothBox(tile, POS.getX(), POS.getY(), POS.getZ(), red, green, blue);
		else
			flatBox(tile, POS.getX(), POS.getY(), POS.getZ(), red, green, blue);
	}
	
	private void flatBox(Tile arg, int posX, int posY, int posZ, float red, float green, float blue) {
		field_92 = false;
		Tessellator tesselator = Tessellator.INSTANCE;
		float var10 = 0.5F;
		float var11 = 1.0F;
		float var12 = 0.8F;
		float var13 = 0.6F;
		float var14 = var11 * red;
		float var15 = var11 * green;
		float var16 = var11 * blue;
		float var17 = var10 * red;
		float var18 = var12 * red;
		float var19 = var13 * red;
		float var20 = var10 * green;
		float var21 = var12 * green;
		float var22 = var13 * green;
		float var23 = var10 * blue;
		float var24 = var12 * blue;
		float var25 = var13 * blue;

		float var26 = arg.method_1604(tileView, posX, posY, posZ);
		if (field_85 || shouldRenderFace(CoreLib.FACE_NEG_Y)) {
			float var27 = arg.method_1604(tileView, posX, posY - 1, posZ);
			tesselator.colour(var17 * var27, var20 * var27, var23 * var27);
			this.method_46(arg, (double)posX, (double)posY, (double)posZ, arg.method_1626(tileView, posX, posY, posZ, 0));
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_POS_Y)) {
			float var27 = arg.method_1604(tileView, posX, posY + 1, posZ);
			if (POS_MAX.getY() != 1.0D && !arg.material.isLiquid()) {
				var27 = var26;
			}

			tesselator.colour(var14 * var27, var15 * var27, var16 * var27);
			this.method_55(arg, (double)posX, (double)posY, (double)posZ, arg.method_1626(tileView, posX, posY, posZ, 1));
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_NEG_Z)) {
			float var27 = arg.method_1604(tileView, posX, posY, posZ - 1);
			if (POS_MIN.getZ() > 0.0D) {
				var27 = var26;
			}

			tesselator.colour(var18 * var27, var21 * var27, var24 * var27);
			int var28 = arg.method_1626(tileView, posX, posY, posZ, 2);
			this.method_61(arg, (double)posX, (double)posY, (double)posZ, var28);
			if (field_67 && var28 == 3 && field_83 < 0) {
				tesselator.colour(var18 * var27 * red, var21 * var27 * green, var24 * var27 * blue);
				this.method_61(arg, (double)posX, (double)posY, (double)posZ, 38);
			}
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_POS_Z)) {
			float var27 = arg.method_1604(tileView, posX, posY, posZ + 1);
			if (POS_MAX.getZ() < 1.0D) {
				var27 = var26;
			}

			tesselator.colour(var18 * var27, var21 * var27, var24 * var27);
			int var28 = arg.method_1626(tileView, posX, posY, posZ, 3);
			this.method_65(arg, (double)posX, (double)posY, (double)posZ, var28);
			if (field_67 && var28 == 3 && field_83 < 0) {
				tesselator.colour(var18 * var27 * red, var21 * var27 * green, var24 * var27 * blue);
				this.method_65(arg, (double)posX, (double)posY, (double)posZ, 38);
			}
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_NEG_X)) {
			float var27 = arg.method_1604(tileView, posX - 1, posY, posZ);
			if (POS_MIN.getX() > 0.0D) {
				var27 = var26;
			}

			tesselator.colour(var19 * var27, var22 * var27, var25 * var27);
			int var28 = arg.method_1626(tileView, posX, posY, posZ, 4);
			this.method_67(arg, (double)posX, (double)posY, (double)posZ, var28);
			if (field_67 && var28 == 3 && field_83 < 0) {
				tesselator.colour(var19 * var27 * red, var22 * var27 * green, var25 * var27 * blue);
				this.method_67(arg, (double)posX, (double)posY, (double)posZ, 38);
			}
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_POS_X)) {
			float var27 = arg.method_1604(tileView, posX + 1, posY, posZ);
			if (POS_MAX.getX() < 1.0D) {
				var27 = var26;
			}

			tesselator.colour(var19 * var27, var22 * var27, var25 * var27);
			int var28 = arg.method_1626(tileView, posX, posY, posZ, 5);
			this.method_69(arg, (double)posX, (double)posY, (double)posZ, var28);
			if (field_67 && var28 == 3 && field_83 < 0) {
				tesselator.colour(var19 * var27 * red, var22 * var27 * green, var25 * var27 * blue);
				this.method_69(arg, (double)posX, (double)posY, (double)posZ, 38);
			}
		}
	}

	private void smoothBox(Tile arg, int posX, int posY, int posZ, float red, float green, float blue) {
		field_92 = true;
		float var9 = field_93;
		float var10 = field_93;
		float var11 = field_93;
		float var12 = field_93;
		int var13 = 1;
		int var14 = 1;
		int var15 = 1;
		int var16 = 1;
		int var17 = 1;
		int var18 = 1;
		field_93 = arg.method_1604(tileView, posX, posY, posZ);
		field_94 = arg.method_1604(tileView, posX - 1, posY, posZ);
		field_95 = arg.method_1604(tileView, posX, posY - 1, posZ);
		field_96 = arg.method_1604(tileView, posX, posY, posZ - 1);
		field_97 = arg.method_1604(tileView, posX + 1, posY, posZ);
		field_98 = arg.method_1604(tileView, posX, posY + 1, posZ);
		field_99 = arg.method_1604(tileView, posX, posY, posZ + 1);
		field_70 = Tile.field_1942[tileView.getTileId(posX + 1, posY + 1, posZ)];
		field_78 = Tile.field_1942[tileView.getTileId(posX + 1, posY - 1, posZ)];
		field_74 = Tile.field_1942[tileView.getTileId(posX + 1, posY, posZ + 1)];
		field_76 = Tile.field_1942[tileView.getTileId(posX + 1, posY, posZ - 1)];
		field_71 = Tile.field_1942[tileView.getTileId(posX - 1, posY + 1, posZ)];
		field_79 = Tile.field_1942[tileView.getTileId(posX - 1, posY - 1, posZ)];
		field_73 = Tile.field_1942[tileView.getTileId(posX - 1, posY, posZ - 1)];
		field_75 = Tile.field_1942[tileView.getTileId(posX - 1, posY, posZ + 1)];
		field_72 = Tile.field_1942[tileView.getTileId(posX, posY + 1, posZ + 1)];
		field_69 = Tile.field_1942[tileView.getTileId(posX, posY + 1, posZ - 1)];
		field_80 = Tile.field_1942[tileView.getTileId(posX, posY - 1, posZ + 1)];
		field_77 = Tile.field_1942[tileView.getTileId(posX, posY - 1, posZ - 1)];
		if (arg.tex == 3) {
			var18 = 0;
			var17 = 0;
			var16 = 0;
			var15 = 0;
			var13 = 0;
		}

		if (field_83 >= 0) {
			var18 = 0;
			var17 = 0;
			var16 = 0;
			var15 = 0;
			var13 = 0;
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_NEG_Y)) {
			if (field_55 <= 0) {
				var12 = field_95;
				var11 = field_95;
				var10 = field_95;
				var9 = field_95;
			} else {
				--posY;
				field_101 = arg.method_1604(tileView, posX - 1, posY, posZ);
				field_103 = arg.method_1604(tileView, posX, posY, posZ - 1);
				field_104 = arg.method_1604(tileView, posX, posY, posZ + 1);
				field_41 = arg.method_1604(tileView, posX + 1, posY, posZ);
				if (!field_77 && !field_79) {
					field_100 = field_101;
				} else {
					field_100 = arg.method_1604(tileView, posX - 1, posY, posZ - 1);
				}

				if (!field_80 && !field_79) {
					field_102 = field_101;
				} else {
					field_102 = arg.method_1604(tileView, posX - 1, posY, posZ + 1);
				}

				if (!field_77 && !field_78) {
					field_105 = field_41;
				} else {
					field_105 = arg.method_1604(tileView, posX + 1, posY, posZ - 1);
				}

				if (!field_80 && !field_78) {
					field_42 = field_41;
				} else {
					field_42 = arg.method_1604(tileView, posX + 1, posY, posZ + 1);
				}

				++posY;
				var9 = (field_102 + field_101 + field_104 + field_95) / 4.0F;
				var12 = (field_104 + field_95 + field_42 + field_41) / 4.0F;
				var11 = (field_95 + field_103 + field_41 + field_105) / 4.0F;
				var10 = (field_101 + field_100 + field_95 + field_103) / 4.0F;
			}

			field_56 = field_57 = field_58 = field_59 = (var13 != 0 ? red : 1.0F) * 0.5F;
			field_60 = field_61 = field_62 = field_63 = (var13 != 0 ? green : 1.0F) * 0.5F;
			field_64 = field_65 = field_66 = field_68 = (var13 != 0 ? blue : 1.0F) * 0.5F;
			field_56 *= var9;
			field_60 *= var9;
			field_64 *= var9;
			field_57 *= var10;
			field_61 *= var10;
			field_65 *= var10;
			field_58 *= var11;
			field_62 *= var11;
			field_66 *= var11;
			field_59 *= var12;
			field_63 *= var12;
			field_68 *= var12;
			this.method_46(arg, (double) posX, (double) posY, (double) posZ, arg.method_1626(tileView, posX, posY, posZ, 0));
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_POS_Y)) {
			if (field_55 <= 0) {
				var12 = field_98;
				var11 = field_98;
				var10 = field_98;
				var9 = field_98;
			} else {
				++posY;
				field_44 = arg.method_1604(tileView, posX - 1, posY, posZ);
				field_48 = arg.method_1604(tileView, posX + 1, posY, posZ);
				field_46 = arg.method_1604(tileView, posX, posY, posZ - 1);
				field_49 = arg.method_1604(tileView, posX, posY, posZ + 1);
				if (!field_69 && !field_71) {
					field_43 = field_44;
				} else {
					field_43 = arg.method_1604(tileView, posX - 1, posY, posZ - 1);
				}

				if (!field_69 && !field_70) {
					field_47 = field_48;
				} else {
					field_47 = arg.method_1604(tileView, posX + 1, posY, posZ - 1);
				}

				if (!field_72 && !field_71) {
					field_45 = field_44;
				} else {
					field_45 = arg.method_1604(tileView, posX - 1, posY, posZ + 1);
				}

				if (!field_72 && !field_70) {
					field_50 = field_48;
				} else {
					field_50 = arg.method_1604(tileView, posX + 1, posY, posZ + 1);
				}

				--posY;
				var12 = (field_45 + field_44 + field_49 + field_98) / 4.0F;
				var9 = (field_49 + field_98 + field_50 + field_48) / 4.0F;
				var10 = (field_98 + field_46 + field_48 + field_47) / 4.0F;
				var11 = (field_44 + field_43 + field_98 + field_46) / 4.0F;
			}

			field_56 = field_57 = field_58 = field_59 = var14 != 0 ? red : 1.0F;
			field_60 = field_61 = field_62 = field_63 = var14 != 0 ? green : 1.0F;
			field_64 = field_65 = field_66 = field_68 = var14 != 0 ? blue : 1.0F;
			field_56 *= var9;
			field_60 *= var9;
			field_64 *= var9;
			field_57 *= var10;
			field_61 *= var10;
			field_65 *= var10;
			field_58 *= var11;
			field_62 *= var11;
			field_66 *= var11;
			field_59 *= var12;
			field_63 *= var12;
			field_68 *= var12;
			this.method_55(arg, (double) posX, (double) posY, (double) posZ, arg.method_1626(tileView, posX, posY, posZ, 1));
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_NEG_Z)) {
			if (field_55 <= 0) {
				var12 = field_96;
				var11 = field_96;
				var10 = field_96;
				var9 = field_96;
			} else {
				--posZ;
				field_51 = arg.method_1604(tileView, posX - 1, posY, posZ);
				field_103 = arg.method_1604(tileView, posX, posY - 1, posZ);
				field_46 = arg.method_1604(tileView, posX, posY + 1, posZ);
				field_52 = arg.method_1604(tileView, posX + 1, posY, posZ);
				if (!field_73 && !field_77) {
					field_100 = field_51;
				} else {
					field_100 = arg.method_1604(tileView, posX - 1, posY - 1, posZ);
				}

				if (!field_73 && !field_69) {
					field_43 = field_51;
				} else {
					field_43 = arg.method_1604(tileView, posX - 1, posY + 1, posZ);
				}

				if (!field_76 && !field_77) {
					field_105 = field_52;
				} else {
					field_105 = arg.method_1604(tileView, posX + 1, posY - 1, posZ);
				}

				if (!field_76 && !field_69) {
					field_47 = field_52;
				} else {
					field_47 = arg.method_1604(tileView, posX + 1, posY + 1, posZ);
				}

				++posZ;
				var9 = (field_51 + field_43 + field_96 + field_46) / 4.0F;
				var10 = (field_96 + field_46 + field_52 + field_47) / 4.0F;
				var11 = (field_103 + field_96 + field_105 + field_52) / 4.0F;
				var12 = (field_100 + field_51 + field_103 + field_96) / 4.0F;
			}

			field_56 = field_57 = field_58 = field_59 = (var15 != 0 ? red : 1.0F) * 0.8F;
			field_60 = field_61 = field_62 = field_63 = (var15 != 0 ? green : 1.0F) * 0.8F;
			field_64 = field_65 = field_66 = field_68 = (var15 != 0 ? blue : 1.0F) * 0.8F;
			field_56 *= var9;
			field_60 *= var9;
			field_64 *= var9;
			field_57 *= var10;
			field_61 *= var10;
			field_65 *= var10;
			field_58 *= var11;
			field_62 *= var11;
			field_66 *= var11;
			field_59 *= var12;
			field_63 *= var12;
			field_68 *= var12;
			int var19 = arg.method_1626(tileView, posX, posY, posZ, 2);
			this.method_61(arg, (double) posX, (double) posY, (double) posZ, var19);
			if (field_67 && var19 == 3 && field_83 < 0) {
				field_56 *= red;
				field_57 *= red;
				field_58 *= red;
				field_59 *= red;
				field_60 *= green;
				field_61 *= green;
				field_62 *= green;
				field_63 *= green;
				field_64 *= blue;
				field_65 *= blue;
				field_66 *= blue;
				field_68 *= blue;
				this.method_61(arg, (double) posX, (double) posY, (double) posZ, 38);
			}
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_POS_Z)) {
			if (field_55 <= 0) {
				var12 = field_99;
				var11 = field_99;
				var10 = field_99;
				var9 = field_99;
			} else {
				++posZ;
				field_53 = arg.method_1604(tileView, posX - 1, posY, posZ);
				field_54 = arg.method_1604(tileView, posX + 1, posY, posZ);
				field_104 = arg.method_1604(tileView, posX, posY - 1, posZ);
				field_49 = arg.method_1604(tileView, posX, posY + 1, posZ);
				if (!field_75 && !field_80) {
					field_102 = field_53;
				} else {
					field_102 = arg.method_1604(tileView, posX - 1, posY - 1, posZ);
				}

				if (!field_75 && !field_72) {
					field_45 = field_53;
				} else {
					field_45 = arg.method_1604(tileView, posX - 1, posY + 1, posZ);
				}

				if (!field_74 && !field_80) {
					field_42 = field_54;
				} else {
					field_42 = arg.method_1604(tileView, posX + 1, posY - 1, posZ);
				}

				if (!field_74 && !field_72) {
					field_50 = field_54;
				} else {
					field_50 = arg.method_1604(tileView, posX + 1, posY + 1, posZ);
				}

				--posZ;
				var9 = (field_53 + field_45 + field_99 + field_49) / 4.0F;
				var12 = (field_99 + field_49 + field_54 + field_50) / 4.0F;
				var11 = (field_104 + field_99 + field_42 + field_54) / 4.0F;
				var10 = (field_102 + field_53 + field_104 + field_99) / 4.0F;
			}

			field_56 = field_57 = field_58 = field_59 = (var16 != 0 ? red : 1.0F) * 0.8F;
			field_60 = field_61 = field_62 = field_63 = (var16 != 0 ? green : 1.0F) * 0.8F;
			field_64 = field_65 = field_66 = field_68 = (var16 != 0 ? blue : 1.0F) * 0.8F;
			field_56 *= var9;
			field_60 *= var9;
			field_64 *= var9;
			field_57 *= var10;
			field_61 *= var10;
			field_65 *= var10;
			field_58 *= var11;
			field_62 *= var11;
			field_66 *= var11;
			field_59 *= var12;
			field_63 *= var12;
			field_68 *= var12;
			int var19 = arg.method_1626(tileView, posX, posY, posZ, 3);
			this.method_65(arg, (double) posX, (double) posY, (double) posZ, arg.method_1626(tileView, posX, posY, posZ, 3));
			if (field_67 && var19 == 3 && field_83 < 0) {
				field_56 *= red;
				field_57 *= red;
				field_58 *= red;
				field_59 *= red;
				field_60 *= green;
				field_61 *= green;
				field_62 *= green;
				field_63 *= green;
				field_64 *= blue;
				field_65 *= blue;
				field_66 *= blue;
				field_68 *= blue;
				this.method_65(arg, (double) posX, (double) posY, (double) posZ, 38);
			}
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_NEG_X)) {
			if (field_55 <= 0) {
				var12 = field_94;
				var11 = field_94;
				var10 = field_94;
				var9 = field_94;
			} else {
				--posX;
				field_101 = arg.method_1604(tileView, posX, posY - 1, posZ);
				field_51 = arg.method_1604(tileView, posX, posY, posZ - 1);
				field_53 = arg.method_1604(tileView, posX, posY, posZ + 1);
				field_44 = arg.method_1604(tileView, posX, posY + 1, posZ);
				if (!field_73 && !field_79) {
					field_100 = field_51;
				} else {
					field_100 = arg.method_1604(tileView, posX, posY - 1, posZ - 1);
				}

				if (!field_75 && !field_79) {
					field_102 = field_53;
				} else {
					field_102 = arg.method_1604(tileView, posX, posY - 1, posZ + 1);
				}

				if (!field_73 && !field_71) {
					field_43 = field_51;
				} else {
					field_43 = arg.method_1604(tileView, posX, posY + 1, posZ - 1);
				}

				if (!field_75 && !field_71) {
					field_45 = field_53;
				} else {
					field_45 = arg.method_1604(tileView, posX, posY + 1, posZ + 1);
				}

				++posX;
				var12 = (field_101 + field_102 + field_94 + field_53) / 4.0F;
				var9 = (field_94 + field_53 + field_44 + field_45) / 4.0F;
				var10 = (field_51 + field_94 + field_43 + field_44) / 4.0F;
				var11 = (field_100 + field_101 + field_51 + field_94) / 4.0F;
			}

			field_56 = field_57 = field_58 = field_59 = (var17 != 0 ? red : 1.0F) * 0.6F;
			field_60 = field_61 = field_62 = field_63 = (var17 != 0 ? green : 1.0F) * 0.6F;
			field_64 = field_65 = field_66 = field_68 = (var17 != 0 ? blue : 1.0F) * 0.6F;
			field_56 *= var9;
			field_60 *= var9;
			field_64 *= var9;
			field_57 *= var10;
			field_61 *= var10;
			field_65 *= var10;
			field_58 *= var11;
			field_62 *= var11;
			field_66 *= var11;
			field_59 *= var12;
			field_63 *= var12;
			field_68 *= var12;
			int var19 = arg.method_1626(tileView, posX, posY, posZ, 4);
			this.method_67(arg, (double) posX, (double) posY, (double) posZ, var19);
			if (field_67 && var19 == 3 && field_83 < 0) {
				field_56 *= red;
				field_57 *= red;
				field_58 *= red;
				field_59 *= red;
				field_60 *= green;
				field_61 *= green;
				field_62 *= green;
				field_63 *= green;
				field_64 *= blue;
				field_65 *= blue;
				field_66 *= blue;
				field_68 *= blue;
				this.method_67(arg, (double) posX, (double) posY, (double) posZ, 38);
			}
		}

		if (field_85 || shouldRenderFace(CoreLib.FACE_POS_X)) {
			if (field_55 <= 0) {
				var12 = field_97;
				var11 = field_97;
				var10 = field_97;
				var9 = field_97;
			} else {
				++posX;
				field_41 = arg.method_1604(tileView, posX, posY - 1, posZ);
				field_52 = arg.method_1604(tileView, posX, posY, posZ - 1);
				field_54 = arg.method_1604(tileView, posX, posY, posZ + 1);
				field_48 = arg.method_1604(tileView, posX, posY + 1, posZ);
				if (!field_78 && !field_76) {
					field_105 = field_52;
				} else {
					field_105 = arg.method_1604(tileView, posX, posY - 1, posZ - 1);
				}

				if (!field_78 && !field_74) {
					field_42 = field_54;
				} else {
					field_42 = arg.method_1604(tileView, posX, posY - 1, posZ + 1);
				}

				if (!field_70 && !field_76) {
					field_47 = field_52;
				} else {
					field_47 = arg.method_1604(tileView, posX, posY + 1, posZ - 1);
				}

				if (!field_70 && !field_74) {
					field_50 = field_54;
				} else {
					field_50 = arg.method_1604(tileView, posX, posY + 1, posZ + 1);
				}

				--posX;
				var9 = (field_41 + field_42 + field_97 + field_54) / 4.0F;
				var12 = (field_97 + field_54 + field_48 + field_50) / 4.0F;
				var11 = (field_52 + field_97 + field_47 + field_48) / 4.0F;
				var10 = (field_105 + field_41 + field_52 + field_97) / 4.0F;
			}

			field_56 = field_57 = field_58 = field_59 = (var18 != 0 ? red : 1.0F) * 0.6F;
			field_60 = field_61 = field_62 = field_63 = (var18 != 0 ? green : 1.0F) * 0.6F;
			field_64 = field_65 = field_66 = field_68 = (var18 != 0 ? blue : 1.0F) * 0.6F;
			field_56 *= var9;
			field_60 *= var9;
			field_64 *= var9;
			field_57 *= var10;
			field_61 *= var10;
			field_65 *= var10;
			field_58 *= var11;
			field_62 *= var11;
			field_66 *= var11;
			field_59 *= var12;
			field_63 *= var12;
			field_68 *= var12;
			int var19 = arg.method_1626(tileView, posX, posY, posZ, 5);
			this.method_69(arg, (double) posX, (double) posY, (double) posZ, var19);
			if (field_67 && var19 == 3 && field_83 < 0) {
				field_56 *= red;
				field_57 *= red;
				field_58 *= red;
				field_59 *= red;
				field_60 *= green;
				field_61 *= green;
				field_62 *= green;
				field_63 *= green;
				field_64 *= blue;
				field_65 *= blue;
				field_66 *= blue;
				field_68 *= blue;
				this.method_69(arg, (double) posX, (double) posY, (double) posZ, 38);
			}
		}

		field_92 = false;
	}

	public void method_69(Tile arg, double d, double d1, double d2, int i) {
		Tessellator var9 = Tessellator.INSTANCE;
		if (field_83 >= 0) {
			i = field_83;
		}

		float var10 = uv.getStart().getX();
		float var11 = uv.getStart().getY();
		float dx = uv.getEnd().getX() - uv.getStart().getX();
		float dy = uv.getEnd().getY() - uv.getStart().getY();
		double var12 = var10 + POS_MIN.getZ() * dx;
		double var14 = var10 + POS_MAX.getZ() * dx;
		double var16 = (var11 + dy) - POS_MAX.getY() * dy;
		double var18 = (var11 + dy) - POS_MIN.getY() * dy;
		if (field_84) {
			double var20 = var12;
			var12 = var14;
			var14 = var20;
		}

		if (POS_MIN.getZ() < 0.0D || POS_MAX.getZ() > 1.0D) {
			var12 = var10;
			var14 = var10 + dx;
		}

		if (POS_MIN.getY() < 0.0D || POS_MAX.getY() > 1.0D) {
			var16 = var11;
			var18 = var11 + dy;
		}

		double var42 = var14;
		double var22 = var12;
		double var24 = var16;
		double var26 = var18;
		if (field_88 == 2) {
			var12 = var10 + POS_MIN.getY() * dx;
			var16 = (var11 + dy) - POS_MIN.getZ() * dy;
			var14 = var10 + POS_MAX.getY() * dx;
			var18 = (var11 + 16) - POS_MAX.getZ() * dy;
			var24 = var16;
			var26 = var18;
			var42 = var12;
			var22 = var14;
			var16 = var18;
			var18 = var16;
		} else if (field_88 == 1) {
			var12 = (var10 + dx) - POS_MAX.getY() * dx;
			var16 = var11 + POS_MAX.getZ() * dy;
			var14 = (var10 + dx) - POS_MIN.getY() * dx;
			var18 = var11 + POS_MIN.getZ() * dy;
			var42 = var14;
			var22 = var12;
			var12 = var14;
			var14 = var12;
			var24 = var18;
			var26 = var16;
		} else if (field_88 == 3) {
			var12 = (var10 + dx) - POS_MIN.getZ() * dx;
			var14 = (var10 + dx) - POS_MAX.getZ() * dx;
			var16 = var11 + POS_MAX.getY() * dy;
			var18 = var11 + POS_MIN.getY() * dy;
			var42 = var14;
			var22 = var12;
			var24 = var16;
			var26 = var18;
		}

		double var28 = d + POS_MAX.getX();
		double var30 = d1 + POS_MIN.getY();
		double var32 = d1 + POS_MAX.getY();
		double var34 = d2 + POS_MIN.getZ();
		double var36 = d2 + POS_MAX.getZ();
		if (field_92) {
			var9.colour(field_56, field_60, field_64);
			var9.vertex(var28, var30, var36, var22, var26);
			var9.colour(field_57, field_61, field_65);
			var9.vertex(var28, var30, var34, var14, var18);
			var9.colour(field_58, field_62, field_66);
			var9.vertex(var28, var32, var34, var42, var24);
			var9.colour(field_59, field_63, field_68);
			var9.vertex(var28, var32, var36, var12, var16);
		} else {
			var9.vertex(var28, var30, var36, var22, var26);
			var9.vertex(var28, var30, var34, var14, var18);
			var9.vertex(var28, var32, var34, var42, var24);
			var9.vertex(var28, var32, var36, var12, var16);
		}
	}

	public void method_46(Tile arg, double d, double d1, double d2, int i) {
		Tessellator var9 = Tessellator.INSTANCE;
		if (field_83 >= 0) {
			i = field_83;
		}

		float var10 = uv.getStart().getX();
		float var11 = uv.getStart().getY();
		float dx = uv.getEnd().getX() - uv.getStart().getX();
		float dy = uv.getEnd().getY() - uv.getStart().getY();
		double var12 = var10 + POS_MIN.getX() * dx;
		double var14 = var10 + POS_MAX.getX() * dx;
		double var16 = var11 + POS_MIN.getZ() * dy;
		double var18 = var11 + POS_MAX.getZ() * dy;
		if (POS_MIN.getX() < 0.0D || POS_MAX.getX() > 1.0D) {
			var12 = var10;
			var14 = var10 + dx;
		}

		if (POS_MIN.getZ() < 0.0D || POS_MAX.getZ() > 1.0D) {
			var16 = var11;
			var18 = var11 + dy;
		}

		double var20 = var14;
		double var22 = var12;
		double var24 = var16;
		double var26 = var18;
		if (field_91 == 2) {
			var12 = var10 + POS_MIN.getZ() * dx;
			var16 = (var11 + dy) - POS_MAX.getX() * dy;
			var14 = var10 + POS_MAX.getZ() * dx;
			var18 = (var11 + dy) - POS_MIN.getX() * dy;
			var24 = var16;
			var26 = var18;
			var20 = var12;
			var22 = var14;
			var16 = var18;
			var18 = var16;
		} else if (field_91 == 1) {
			var12 = (var10 + dx) - POS_MAX.getZ() * dx;
			var16 = var11 + POS_MIN.getX() * dy;
			var14 = (var10 + dx) - POS_MIN.getZ() * dx;
			var18 = var11 + POS_MAX.getX() * dy;
			var20 = var14;
			var22 = var12;
			var12 = var14;
			var14 = var12;
			var24 = var18;
			var26 = var16;
		} else if (field_91 == 3) {
			var12 = (var10 + dx) - POS_MIN.getX() * dx;
			var14 = (var10 + dx) - POS_MAX.getX() * dx;
			var16 = (var11 + dy) - POS_MIN.getZ() * dy;
			var18 = (var11 + dy) - POS_MAX.getZ() * dy;
			var20 = var14;
			var22 = var12;
			var24 = var16;
			var26 = var18;
		}

		double var28 = d + POS_MIN.getX();
		double var30 = d + POS_MAX.getX();
		double var32 = d1 + POS_MIN.getY();
		double var34 = d2 + POS_MIN.getZ();
		double var36 = d2 + POS_MAX.getZ();
		if (field_92) {
			var9.colour(field_56, field_60, field_64);
			var9.vertex(var28, var32, var36, var22, var26);
			var9.colour(field_57, field_61, field_65);
			var9.vertex(var28, var32, var34, var12, var16);
			var9.colour(field_58, field_62, field_66);
			var9.vertex(var30, var32, var34, var20, var24);
			var9.colour(field_59, field_63, field_68);
			var9.vertex(var30, var32, var36, var14, var18);
		} else {
			var9.vertex(var28, var32, var36, var22, var26);
			var9.vertex(var28, var32, var34, var12, var16);
			var9.vertex(var30, var32, var34, var20, var24);
			var9.vertex(var30, var32, var36, var14, var18);
		}
	}

	public void method_55(Tile arg, double d, double d1, double d2, int i) {
		Tessellator var9 = Tessellator.INSTANCE;
		if (field_83 >= 0) {
			i = field_83;
		}

		float var10 = uv.getStart().getX();
		float var11 = uv.getStart().getY();
		float dx = uv.getEnd().getX() - uv.getStart().getX();
		float dy = uv.getEnd().getY() - uv.getStart().getY();
		double var12 = var10 + POS_MIN.getX() * dx;
		double var14 = var10 + POS_MAX.getX() * dx;
		double var16 = var11 + POS_MIN.getZ() * dy;
		double var18 = var11 + POS_MAX.getZ() * dy;
		if (POS_MIN.getX() < 0.0D || POS_MAX.getX() > 1.0D) {
			var12 = var10;
			var14 = var10 + dx;
		}

		if (POS_MIN.getZ() < 0.0D || POS_MAX.getZ() > 1.0D) {
			var16 = var11;
			var18 = var11 + dy;
		}

		double var20 = var14;
		double var22 = var12;
		double var24 = var16;
		double var26 = var18;
		if (field_90 == 1) {
			var12 = var10 + POS_MIN.getZ() * dx;
			var16 = (var11 + dy) - POS_MAX.getX() * dy;
			var14 = var10 + POS_MAX.getZ() * dx;
			var18 = (var11 + dy) - POS_MIN.getX() * dy;
			var24 = var16;
			var26 = var18;
			var20 = var12;
			var22 = var14;
			var16 = var18;
			var18 = var16;
		} else if (field_90 == 2) {
			var12 = (var10 + dx) - POS_MAX.getZ() * dx;
			var16 = var11 + POS_MIN.getX() * dy;
			var14 = (var10 + dx) - POS_MIN.getZ() * dx;
			var18 = var11 + POS_MAX.getX() * dy;
			var20 = var14;
			var22 = var12;
			var12 = var14;
			var14 = var12;
			var24 = var18;
			var26 = var16;
		} else if (field_90 == 3) {
			var12 = (var10 + dx) - POS_MIN.getX() * dx;
			var14 = (var10 + dx) - POS_MAX.getX() * dx;
			var16 = (var11 + dy) - POS_MIN.getZ() * dy;
			var18 = (var11 + dy) - POS_MAX.getZ() * dy;
			var20 = var14;
			var22 = var12;
			var24 = var16;
			var26 = var18;
		}

		double var28 = d + POS_MIN.getX();
		double var30 = d + POS_MAX.getX();
		double var32 = d1 + POS_MAX.getY();
		double var34 = d2 + POS_MIN.getZ();
		double var36 = d2 + POS_MAX.getZ();
		if (field_92) {
			var9.colour(field_56, field_60, field_64);
			var9.vertex(var30, var32, var36, var14, var18);
			var9.colour(field_57, field_61, field_65);
			var9.vertex(var30, var32, var34, var20, var24);
			var9.colour(field_58, field_62, field_66);
			var9.vertex(var28, var32, var34, var12, var16);
			var9.colour(field_59, field_63, field_68);
			var9.vertex(var28, var32, var36, var22, var26);
		} else {
			var9.vertex(var30, var32, var36, var14, var18);
			var9.vertex(var30, var32, var34, var20, var24);
			var9.vertex(var28, var32, var34, var12, var16);
			var9.vertex(var28, var32, var36, var22, var26);
		}
	}

	public void method_61(Tile arg, double d, double d1, double d2, int i) {
		Tessellator var9 = Tessellator.INSTANCE;
		if (field_83 >= 0) {
			i = field_83;
		}

		float var10 = uv.getStart().getX();
		float var11 = uv.getStart().getY();
		float dx = uv.getEnd().getX() - uv.getStart().getX();
		float dy = uv.getEnd().getY() - uv.getStart().getY();
		double var12 = var10 + POS_MIN.getX() * dx;
		double var14 = var10 + POS_MAX.getX() * dx;
		double var16 = (var11 + dy) - POS_MAX.getY() * dy;
		double var18 = (var11 + dy) - POS_MIN.getY() * dy;
		if (field_84) {
			double var20 = var12;
			var12 = var14;
			var14 = var20;
		}

		if (POS_MIN.getX() < 0.0D || POS_MAX.getX() > 1.0D) {
			var12 = var10;
			var14 = var10 + dx;
		}

		if (POS_MIN.getY() < 0.0D || POS_MAX.getY() > 1.0D) {
			var16 = var11;
			var18 = var11 + dy;
		}

		double var42 = var14;
		double var22 = var12;
		double var24 = var16;
		double var26 = var18;
		if (field_86 == 2) {
			var12 = var10 + POS_MIN.getY() * dx;
			var16 = (var11 + dy) - POS_MIN.getX() * dy;
			var14 = var10 + POS_MAX.getY() * dx;
			var18 = (var11 + dy) - POS_MAX.getX() * dy;
			var24 = var16;
			var26 = var18;
			var42 = var12;
			var22 = var14;
			var16 = var18;
			var18 = var16;
		} else if (field_86 == 1) {
			var12 = (var10 + dx) - POS_MAX.getY() * dx;
			var16 = var11 + POS_MAX.getX() * dy;
			var14 = (var10 + dx) - POS_MIN.getY() * dx;
			var18 = var11 + POS_MIN.getX() * dy;
			var42 = var14;
			var22 = var12;
			var12 = var14;
			var14 = var12;
			var24 = var18;
			var26 = var16;
		} else if (field_86 == 3) {
			var12 = (var10 + dx) - POS_MIN.getX() * dx;
			var14 = (var10 + dx) - POS_MAX.getX() * dx;
			var16 = var11 + POS_MAX.getY() * dy;
			var18 = var11 + POS_MIN.getY() * dy;
			var42 = var14;
			var22 = var12;
			var24 = var16;
			var26 = var18;
		}

		double var28 = d + POS_MIN.getX();
		double var30 = d + POS_MAX.getX();
		double var32 = d1 + POS_MIN.getY();
		double var34 = d1 + POS_MAX.getY();
		double var36 = d2 + POS_MIN.getZ();
		if (field_92) {
			var9.colour(field_56, field_60, field_64);
			var9.vertex(var28, var34, var36, var42, var24);
			var9.colour(field_57, field_61, field_65);
			var9.vertex(var30, var34, var36, var12, var16);
			var9.colour(field_58, field_62, field_66);
			var9.vertex(var30, var32, var36, var22, var26);
			var9.colour(field_59, field_63, field_68);
			var9.vertex(var28, var32, var36, var14, var18);
		} else {
			var9.vertex(var28, var34, var36, var42, var24);
			var9.vertex(var30, var34, var36, var12, var16);
			var9.vertex(var30, var32, var36, var22, var26);
			var9.vertex(var28, var32, var36, var14, var18);
		}
	}

	public void method_65(Tile arg, double d, double d1, double d2, int i) {
		Tessellator var9 = Tessellator.INSTANCE;
		if (field_83 >= 0) {
			i = field_83;
		}

		float var10 = uv.getStart().getX();
		float var11 = uv.getStart().getY();
		float dx = uv.getEnd().getX() - uv.getStart().getX();
		float dy = uv.getEnd().getY() - uv.getStart().getY();
		double var12 = var10 + POS_MIN.getX() * dx;
		double var14 = var10 + POS_MAX.getX() * dx;
		double var16 = (var11 + dy) - POS_MAX.getY() * dy;
		double var18 = (var11 + dy) - POS_MIN.getY() * dy;
		if (field_84) {
			double var20 = var12;
			var12 = var14;
			var14 = var20;
		}

		if (POS_MIN.getX() < 0.0D || POS_MAX.getX() > 1.0D) {
			var12 = var10;
			var14 = var10 + dx;
		}

		if (POS_MIN.getY() < 0.0D || POS_MAX.getY() > 1.0D) {
			var16 = var11;
			var18 = var11 + dy;
		}

		double var42 = var14;
		double var22 = var12;
		double var24 = var16;
		double var26 = var18;
		if (field_87 == 1) {
			var12 = ((double) var10 + POS_MIN.getY() * 16.0D) / size;
			var18 = ((double) (var11 + 16) - POS_MIN.getX() * 16.0D) / size;
			var14 = ((double) var10 + POS_MAX.getY() * 16.0D) / size;
			var16 = ((double) (var11 + 16) - POS_MAX.getX() * 16.0D) / size;
			var24 = var16;
			var26 = var18;
			var42 = var12;
			var22 = var14;
			var16 = var18;
			var18 = var16;
		} else if (field_87 == 2) {
			var12 = ((double) (var10 + 16) - POS_MAX.getY() * 16.0D) / size;
			var16 = ((double) var11 + POS_MIN.getX() * 16.0D) / size;
			var14 = ((double) (var10 + 16) - POS_MIN.getY() * 16.0D) / size;
			var18 = ((double) var11 + POS_MAX.getX() * 16.0D) / size;
			var42 = var14;
			var22 = var12;
			var12 = var14;
			var14 = var12;
			var24 = var18;
			var26 = var16;
		} else if (field_87 == 3) {
			var12 = ((double) (var10 + 16) - POS_MIN.getX() * 16.0D) / size;
			var14 = ((double) (var10 + 16) - POS_MAX.getX() * 16.0D - 0.01D) / size;
			var16 = ((double) var11 + POS_MAX.getY() * 16.0D) / size;
			var18 = ((double) var11 + POS_MIN.getY() * 16.0D - 0.01D) / size;
			var42 = var14;
			var22 = var12;
			var24 = var16;
			var26 = var18;
		}

		double var28 = d + POS_MIN.getX();
		double var30 = d + POS_MAX.getX();
		double var32 = d1 + POS_MIN.getY();
		double var34 = d1 + POS_MAX.getY();
		double var36 = d2 + POS_MAX.getZ();
		if (field_92) {
			var9.colour(field_56, field_60, field_64);
			var9.vertex(var28, var34, var36, var12, var16);
			var9.colour(field_57, field_61, field_65);
			var9.vertex(var28, var32, var36, var22, var26);
			var9.colour(field_58, field_62, field_66);
			var9.vertex(var30, var32, var36, var14, var18);
			var9.colour(field_59, field_63, field_68);
			var9.vertex(var30, var34, var36, var42, var24);
		} else {
			var9.vertex(var28, var34, var36, var12, var16);
			var9.vertex(var28, var32, var36, var22, var26);
			var9.vertex(var30, var32, var36, var14, var18);
			var9.vertex(var30, var34, var36, var42, var24);
		}
	}

	public void method_67(Tile arg, double d, double d1, double d2, int i) {
		Tessellator var9 = Tessellator.INSTANCE;
		if (field_83 >= 0) {
			i = field_83;
		}

		float u = uv.getStart().getX();
		float v = uv.getStart().getY();
		float dx = uv.getEnd().getX() - uv.getStart().getX();
		float dy = uv.getEnd().getY() - uv.getStart().getY();
		double u1 = u + POS_MIN.getZ() * dx;
		double u2 = u + POS_MAX.getZ() * dx;
		double v1 = (v + dy) - POS_MAX.getY() * dy;
		double v2 = (v + dy) - POS_MIN.getY() * dy;
		if (field_84) {
			double var20 = u1;
			u1 = u2;
			u2 = var20;
		}

		if (POS_MIN.getZ() < 0.0D || POS_MAX.getZ() > 1.0D) {
			u1 = u;
			u2 = u + dx;
		}

		if (POS_MIN.getY() < 0.0D || POS_MAX.getY() > 1.0D) {
			v1 = v;
			v2 = v + dy;
		}

		double u3 = u2;
		double u4 = u1;
		double v3 = v1;
		double v4 = v2;
		if (field_89 == 1) {
			u1 = u + POS_MIN.getY() * dx;
			v1 = (v + dy) - POS_MAX.getZ() * dy;
			u2 = u + POS_MAX.getY() * dx;
			v2 = (v + dy) - POS_MIN.getZ() * dy;
			v3 = v1;
			v4 = v2;
			u3 = u1;
			u4 = u2;
			v1 = v2;
			v2 = v1;
		} else if (field_89 == 2) {
			u1 = (u + dx) - POS_MAX.getY() * dx;
			v1 = v + POS_MIN.getZ() * dy;
			u2 = (u + dx) - POS_MIN.getY() * dx;
			v2 = v + POS_MAX.getZ() * dy;
			u3 = u2;
			u4 = u1;
			u1 = u2;
			u2 = u1;
			v3 = v2;
			v4 = v1;
		} else if (field_89 == 3) {
			u1 = (u + 16) - POS_MIN.getZ() * dx;
			u2 = (u + 16) - POS_MAX.getZ() * dx;
			v1 = v + POS_MAX.getY() * dy;
			v2 = v + POS_MIN.getY() * dy;
			u3 = u2;
			u4 = u1;
			v3 = v1;
			v4 = v2;
		}

		double var28 = d + POS_MIN.getX();
		double var30 = d1 + POS_MIN.getY();
		double var32 = d1 + POS_MAX.getY();
		double var34 = d2 + POS_MIN.getZ();
		double var36 = d2 + POS_MAX.getZ();
		if (field_92) {
			var9.colour(field_56, field_60, field_64);
			var9.vertex(var28, var32, var36, u3, v3);
			var9.colour(field_57, field_61, field_65);
			var9.vertex(var28, var32, var34, u1, v1);
			var9.colour(field_58, field_62, field_66);
			var9.vertex(var28, var30, var34, u4, v4);
			var9.colour(field_59, field_63, field_68);
			var9.vertex(var28, var30, var36, u2, v2);
		} else {
			var9.vertex(var28, var32, var36, u3, v3);
			var9.vertex(var28, var32, var34, u1, v1);
			var9.vertex(var28, var30, var34, u4, v4);
			var9.vertex(var28, var30, var36, u2, v2);
		}
	}
}