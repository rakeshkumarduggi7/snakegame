package com.snakegame.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
public class snakegame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion sh;
	TextureRegion sb;
	float x, y;
	BitmapFont bmf;
	float angle;
	float sbangle;
	boolean start;
	Texture snakebody;
	float sbx;
	float sby;
	ArrayList<float[]> al;
	ArrayList<TextureRegion> sba;
	@Override
	public void create() {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("snakhead.jpg"));
		sh = new TextureRegion(img);
		snakebody=new Texture(Gdx.files.internal("snakebody.jpg"));
		sb=new TextureRegion(snakebody);
		x = 350;
		y = 170;
		sbx=318;
		sby=170;
		start =false;
		bmf=new BitmapFont(Gdx.files.internal("fren.fnt"));
		bmf.setColor(Color.GOLD);
		angle=0;
		sbangle=0;
		sba=new ArrayList<>();
		sba.add(sb);
al=new ArrayList<>();
	}
	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		if(!start){
			x=350;
			y=170;
			angle = 0;

			bmf.draw(batch,"press enter to start the game",150,100);
		}
			batch.draw(
					sh,
					x, y,                   // Position of the image
					sh.getRegionWidth() / 2f, sh.getRegionHeight() / 2f,  // Origin of rotation (center of image)
					32, 32,                 // Width and height
					1f, 1f,                 // Scale
					angle                     // Rotation angle in degrees
			);
		for (TextureRegion x:sba){
		batch.draw(
				x,
				sbx, sby,                   // Position of the image
				sb.getRegionWidth() / 2f, sb.getRegionHeight() / 2f,  // Origin of rotation (center of image)
				32, 32,                 // Width and height
				1f, 1f,                 // Scale
				sbangle                     // Rotation angle in degrees
		);
		}
		batch.end();
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER))start=true;
		if (start){
		if(angle==0){
			x+=Gdx.graphics.getDeltaTime()*200;
			if (Gdx.input.isKeyPressed(Input.Keys.UP)){
				angle=90;
				al.add(new float[]{x,y,angle});
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				angle=270;
				al.add(new float[]{x,y,angle});
			}
		} else if (angle==90) {
			y+=Gdx.graphics.getDeltaTime()*200;

			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				angle=180;
				al.add(new float[]{x,y,angle});
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				angle=0;
				al.add(new float[]{x,y,angle});
			}
		}
		else if (angle==180) {
			x-=Gdx.graphics.getDeltaTime()*200;
			if (Gdx.input.isKeyPressed(Input.Keys.UP)){
				angle=90;
				al.add(new float[]{x,y,angle});
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				angle=270;
				al.add(new float[]{x,y,angle});
			}
		}
		else if (angle==270) {
			y-=Gdx.graphics.getDeltaTime()*200;
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				angle=180;
				al.add(new float[]{x,y,angle});
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				angle=0;
				al.add(new float[]{x,y,angle});
			}
		}

			if(sbangle==0){
				sbx+=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0) {
					if (al.get(0)[0]<=sbx){
						sbangle=al.get(0)[2];
						al.remove(0);
					}
				}
			}
			else if (sbangle==180) {
				sbx-=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0) {
					if (al.get(0)[0]>=sbx){
						sbangle=al.get(0)[2];
						al.remove(0);
					}
				}
			}
			else if (sbangle==270) {
				sby-=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0) {
					if (al.get(0)[1]>=sby){
						sbangle=al.get(0)[2];
						al.remove(0);
					}
				}

			}
			else if (sbangle==90) {
				sby+=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0) {
					if (al.get(0)[1]<=sby){
						sbangle=al.get(0)[2];
						al.remove(0);
					}
				}
			}
		}
		if(x>=Gdx.graphics.getWidth()||x<0){
			start=false;
		al.clear();	sbx=318;
			sby=170;
			sbangle=0;
		}
		if(y>=Gdx.graphics.getHeight()||y<0){
			start=false;
		al.clear();	sbx=318;
			sby=170;
			sbangle=0;
		}
	}
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}