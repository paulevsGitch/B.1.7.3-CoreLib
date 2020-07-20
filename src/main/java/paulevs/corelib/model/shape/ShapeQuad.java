package paulevs.corelib.model.shape;

public class ShapeQuad extends Shape
{
	float[] positions = new float[12];

	public ShapeQuad setPoint(int index, float x, float y, float z)
	{
		index *= 3;
		positions[index] = x;
		positions[index + 1] = y;
		positions[index + 2] = z;
		return this;
	}

	@Override
	public void render()
	{

	}
}