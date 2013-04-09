package MazeFactory;

import org.junit.Assert;

import org.junit.Test;

public class MazeFactory_Test {

	@Test
	public void testCell(){
		Cell cell = new Cell();
		cell.breakNorthWall();
		int wall1 = cell.getNorthWall();
		int wall2 = cell.getEastWall();
		int wall3 = cell.getWestWall();
		int wall4 = cell.getSouthWall();
		Assert.assertEquals(0 , wall1);
		Assert.assertEquals(1 , wall2);
		Assert.assertEquals(1, wall3);
		Assert.assertEquals(1, wall4);
	}

}
