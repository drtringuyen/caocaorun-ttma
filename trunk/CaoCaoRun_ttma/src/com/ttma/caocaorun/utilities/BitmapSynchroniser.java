package com.ttma.caocaorun.utilities;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.Display;

public class BitmapSynchroniser {

	private static float defautHeight, defautWidth;

	// private static float ratioW;
	// private static float ratioH;

	private static float ratio;

	@SuppressWarnings("deprecation")
	public static void setInitialParameters(Display display,
			Bitmap standardBitmap) {
		// float standardWidth = standardBitmap.getWidth();
		float standardHeight = standardBitmap.getHeight();
		
		defautHeight = display.getHeight();
		defautWidth = display.getWidth();

		float ratioH = defautHeight / standardHeight;

		ratio = ratioH;
	}

	// ##################################### get source and destination
	// #################################################
	public static Rect getSourceRect(Bitmap bitmap) {

		int bitmapWidth = bitmap.getWidth();
		int bitmapHeight = bitmap.getHeight();

		Rect result = new Rect(0, 0, bitmapWidth, bitmapHeight);
		return result;
	}

	public static Rect getScourceRectFromStoryLine(Bitmap bitmap, int index,
			int total) {

		if (index + 1 > total)
			index = total;

		int bitmapWidth = bitmap.getWidth() % total;
		int bitmapHeight = bitmap.getHeight();

		Rect result = new Rect(index * bitmapWidth, 0, (index + 1)
				* bitmapWidth, bitmapHeight);
		return result;
	}
	
	public static Rect getDestinationFromStoryLine(Bitmap bitmap, float x, float y,
			int numberOfFrame) {

		float bitmapWidth = bitmap.getWidth() * ratio/numberOfFrame;
		float bitmapHeight = bitmap.getHeight() * ratio;

		x = x * defautWidth;
		y = y * defautHeight;

		Rect result = new Rect((int) (x - bitmapWidth / 2),
				(int) (y - bitmapHeight / 2), (int) (x + bitmapWidth / 2),
				(int) (y + bitmapHeight / 2));
		return result;

	}

	public static Rect getScourceRectFromSprite(Bitmap bitmap, int index,
			int column, int row) {

		float bitmapWidth = bitmap.getWidth();
		float bitmapHeight = bitmap.getHeight();

		bitmapHeight /= row;
		bitmapWidth /= column;

		int indexColumn = index % column;
		int indexRow = index / column;

		int left = (int) (indexColumn * bitmapWidth);
		int top = (int) (indexRow * bitmapHeight);
		int right = (int) ((indexColumn + 1) * bitmapWidth);
		int bottom = (int) ((indexRow + 1) * bitmapHeight);

		Rect result = new Rect(left, top, right, bottom);

		return result;
	}

	public static Rect getDestinationRect(Bitmap bitmap, int x, int y,
			boolean fromCenter, boolean scaleXYwithScreen) {

		int bitmapWidth = (int) (bitmap.getWidth() * ratio);
		int bitmapHeight = (int) (bitmap.getHeight() * ratio);

		if (scaleXYwithScreen) {
			x = (int) (x * ratio);
			y = (int) (y * ratio);
		}

		if (!fromCenter) {
			Rect result = new Rect(x, y, x + bitmapWidth, y + bitmapHeight);
			return result;
		}
		Rect result = new Rect(x - bitmapWidth / 2, y - bitmapHeight / 2, x
				+ bitmapWidth / 2, y + bitmapHeight / 2);
		return result;

	}

	public static Rect getDestinationRect(Rect crop, int x, int y,
			boolean fromCenter) {

		int width = crop.right - crop.left;
		int height = crop.bottom - crop.top;

		if (fromCenter) {
			x -= width / 2;
			y -= height / 2;
		}

		return new Rect(x, y, x + width, y + height);
	}

	public static Rect getNextToRightRect(Rect crop, boolean fromCenter,
			float spacing) {

		float width = crop.width();
		float height = crop.height();

		float x = crop.right;
		float y = crop.top;

		if (fromCenter) {
			x = x - width * spacing;
		}

		return new Rect((int) x, (int) y, (int) (x + width), (int) (y + height));
	}
	
	public static Rect getNextToLeftRect(Rect crop, boolean fromCenter,
			float spacing) {

		float width = crop.width();
		float height = crop.height();

		float x = crop.left-width;
		float y = crop.top;

		if (fromCenter) {
			x = x + width * spacing;
		}

		return new Rect((int) x, (int) y, (int) (x + width), (int) (y + height));
	}

	public static Rect getDestinationRect(Bitmap bitmap, float x, float y,
			boolean fromCenter) {

		float bitmapWidth = bitmap.getWidth() * ratio;
		float bitmapHeight = bitmap.getHeight() * ratio;

		x = x * defautWidth;
		y = y * defautHeight;

		if (!fromCenter) {
			Rect result = new Rect((int) x, (int) y, (int) (x + bitmapWidth),
					(int) (y + bitmapHeight));
			return result;
		}
		Rect result = new Rect((int) (x - bitmapWidth / 2),
				(int) (y - bitmapHeight / 2), (int) (x + bitmapWidth / 2),
				(int) (y + bitmapHeight / 2));
		return result;

	}

	public static Rect getDestinationRect(Rect original, float x, float y,
			boolean fromCenter, float size) {

		int width = (int) (original.width() * ratio * size);
		int height = (int) (original.height() * ratio * size);

		x *=defautWidth;
		y *= defautHeight;

		return new Rect((int) x, (int) y, (int) (x + width), (int) (y + height));
	}
	
	public static Rect getDestinationRect(Rect original, float x, float y,
			boolean fromCenter) {

		int width = (int) (original.width()*ratio);
		int height = (int) (original.height()*ratio);

		x *= defautWidth;
		y *= defautHeight;
		
		if (fromCenter){
			x-=width/2;
			y-=height/2;
		}

		return new Rect((int) x, (int) y, (int) (x + width), (int) (y + height));
	}

	// ******************************************************************************************************************

	// ################################### methods to get Synchroniser
	// ##################################################
	public static Rect[] getBackGroundRects(Bitmap bitmap) {
		Rect[] result = new Rect[2];
		result[0] = getSourceRect(bitmap);
		int x=(int) (defautWidth/2);
		int y=(int) (defautHeight/2);
		result[1] = getDestinationRect(bitmap, x, y, true, false);
		return result;
	}
	
//	public static Rect[] getBackGroundRects(Bitmap bitmap, int x, int y,
//			boolean fromCenter, boolean isButton) {
//		Rect[] result = new Rect[2];
//		result[0] = getSourceRect(bitmap);
//		result[1] = getDestinationRect(bitmap, x, y, fromCenter, isButton);
//		return result;
//	}

	public static Rect getBoundaryRect(Rect original, int boundary) {
		getBoundaryRect(original, boundary, boundary, boundary, boundary);
		return original;
	}

	public static Rect getBoundaryRect(Rect original, float extension) {
		int boundary = (int) (extension * (original.right - original.left));
		return getBoundaryRect(original, boundary);
	}

	public static Rect getBoundaryRect(Rect original, int boundaryTop,
			int boundaryRight, int boundaryDown, int boundaryLeft) {

		original.top = original.top - boundaryRight;
		original.right = original.right + boundaryRight;
		original.bottom = original.bottom + boundaryDown;
		original.left = original.left - boundaryLeft;

		return original;
	}

	public static float getDefautWidth() {
		return defautWidth;
	}
	public static float getDefautHeight() {
		return defautHeight;
	}
	// ******************************************************************************************************************
}