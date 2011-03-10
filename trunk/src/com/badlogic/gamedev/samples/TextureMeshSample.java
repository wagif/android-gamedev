package com.badlogic.gamedev.samples;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gamedev.tools.GameActivity;
import com.badlogic.gamedev.tools.GameListener;
import com.badlogic.gamedev.tools.Mesh;
import com.badlogic.gamedev.tools.Texture;
import com.badlogic.gamedev.tools.Mesh.PrimitiveType;
import com.badlogic.gamedev.tools.Texture.TextureFilter;
import com.badlogic.gamedev.tools.Texture.TextureWrap;

public class TextureMeshSample extends GameActivity implements GameListener
{
	Mesh mesh;
	Texture texture;
	
	public void onCreate( Bundle bundle )
	{
		super.onCreate( bundle );
		setGameListener(this);
	}
	
	@Override
	public void setup(GameActivity activity, GL10 gl) 
	{	
		mesh = new Mesh( gl, 3, true, true, false );
		mesh.texCoord( 0, 1 );
		mesh.color( 1, 0, 0, 1 );
		mesh.vertex( -0.5f, -0.5f, 0 );
		mesh.texCoord( 1, 1 );
		mesh.color( 0, 1, 0, 1 );
		mesh.vertex( 0.5f, -0.5f, 0 );
		mesh.texCoord( 0.5f, 0f );
		mesh.color( 0, 0, 1, 1 );
		mesh.vertex( 0, 0.5f, 0);
		
		try
		{
			Bitmap bitmap = BitmapFactory.decodeStream( getAssets().open( "droid.png" ) );
			texture = new Texture( gl, bitmap, TextureFilter.Linear, TextureFilter.Linear, TextureWrap.ClampToEdge, TextureWrap.ClampToEdge );
		}
		catch( Exception ex )
		{
			Log.d("Sample", "oh noes!");
			System.exit(-1);
		}
	}
	
	@Override
	public void mainLoopIteration(GameActivity activity, GL10 gl) 
	{			
		gl.glViewport( 0, 0, activity.getViewportWidth(), activity.getViewportHeight() );
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		gl.glEnable( GL10.GL_TEXTURE_2D );		
		texture.bind();
		mesh.render(PrimitiveType.Triangles);
	}
}
