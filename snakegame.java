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
 import java.util.Random;

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
 ArrayList<float[]> bc;
	ArrayList<int[]> foodco;
	Random r=new Random();
	Texture sf;
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
 bc=new ArrayList<>();
 bc.add(new float[]{318,170,0});
foodco=new ArrayList<>();
		foodco.add(new int[]{r.nextInt(0,700),r.nextInt(0,330)});
		foodco.add(new int[]{r.nextInt(0,700),r.nextInt(0,330)});
		sf=new Texture(Gdx.files.internal("sf.jpg"));
	}
	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0, 1);
		if(foodco.size()==0){
			foodco.add(new int[]{r.nextInt(0,700),r.nextInt(0,330)});
			foodco.add(new int[]{r.nextInt(0,700),r.nextInt(0,330)});
		}
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
		for (int i=0;i<bc.size();i++){
		batch.draw(
			 sb,
				bc.get(i)[0], bc.get(i)[1],                   // Position of the image
				sb.getRegionWidth() / 2f, sb.getRegionHeight() / 2f,  // Origin of rotation (center of image)
				32, 32,                 // Width and height
				1f, 1f,                 // Scale
				bc.get(i)[2]                     // Rotation angle in degrees
		);
		}
		if(start){
		batch.draw(sf,foodco.get(0)[0],foodco.get(0)[1]);}
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
		for (float[] co:bc){
			if(co[2]==0){
				co[0]+=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0) {
					if (al.get(0)[0]<=co[0]){
						co[2]=al.get(0)[2];
						al.remove(0);
					}
				}
			}
			else if (co[2]==180) {
				co[0]-=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0) {
					if (al.get(0)[0]>=co[0]){
						co[2]=al.get(0)[2];
						al.remove(0);
					}
				}
			}
			else if (co[2]==270) {
				co[1]-=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0) {
					if (al.get(0)[1]>=co[1]){
						co[2]=al.get(0)[2];
						al.remove(0);
					}
				}

			}
			else if (co[2]==90) {
				co[1]+=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0) {
					if (al.get(0)[1]<=co[1]){
						co[2]=al.get(0)[2];
						al.remove(0);
					}
				}
			}
		}}
		if (x>=foodco.get(0)[0]&&x<=foodco.get(0)[0]+32 &&y>=foodco.get(0)[1]&&y<=foodco.get(0)[1]+32 ){
			foodco.remove(0);

		}
		if(x>=Gdx.graphics.getWidth()||x<0){
			start=false;
		al.clear();	sbx=318;
			sby=170;
			sbangle=0;
			bc.clear();
			bc.add(new float[]{318,170,0});
		}
		if(y>=Gdx.graphics.getHeight()||y<0){
			start=false;
		al.clear();	sbx=318;
			sby=170;
			sbangle=0;
			bc.clear();
			bc.add(new float[]{318,170,0});
		}
	}
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}