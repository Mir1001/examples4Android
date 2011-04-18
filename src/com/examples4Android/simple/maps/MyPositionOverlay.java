package com.examples4Android.simple.maps;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class MyPositionOverlay extends Overlay {

  private final int mRadius = 8;

  public MyPositionOverlay() {
	  super();
	  locations = new ArrayList<Location>();
  }
  Location location;
  ArrayList<Location> locations;
 
  public Location getLocation() {
    return location;
  }
  public void setLocation(Location location) {
    this.location = location;
  }
	
  @Override
  public boolean onTap(GeoPoint point, MapView mapView) {
    return false;
  }
  
  @Override
  public void draw(Canvas canvas, MapView mapView, boolean shadow) {
    Projection projection = mapView.getProjection();

    if ((location!=null)&&(shadow == false)) {
      // Get the current location    
      Double latitude = location.getLatitude()*1E6;
      Double longitude = location.getLongitude()*1E6;
      GeoPoint geoPoint; 
      geoPoint = new 
        GeoPoint(latitude.intValue(),longitude.intValue());

      // Convert the location to screen pixels     
      Point point = new Point();
      projection.toPixels(geoPoint, point);

      RectF oval = new RectF(point.x - mRadius, point.y - mRadius, 
                             point.x + mRadius, point.y + mRadius);

      // Setup the paint
      Paint paint = new Paint();
      paint.setARGB(250, 255, 255, 255);
      paint.setAntiAlias(true);
      paint.setFakeBoldText(true);

      Paint backPaint = new Paint();
      backPaint.setARGB(175, 50, 50, 50);
      backPaint.setAntiAlias(true);

      RectF backRect = new RectF(point.x + 2 + mRadius, 
                                 point.y - 3*mRadius,
                                 point.x + 65, point.y + mRadius);

      // Draw the marker    
      canvas.drawOval(oval, paint);
      canvas.drawRoundRect(backRect, 5, 5, backPaint);
      canvas.drawText("TUKAJ", 
                      point.x + 2*mRadius+2, point.y, 
                      paint);
    }
    super.draw(canvas, mapView, shadow);
  }
}