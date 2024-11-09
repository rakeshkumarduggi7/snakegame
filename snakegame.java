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
	boolean start;
	Texture snakebody;
	ArrayList<float[]> al;
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
		start =false;
		bmf=new BitmapFont(Gdx.files.internal("fren.fnt"));
		bmf.setColor(Color.GOLD);
		angle=0;


al=new ArrayList<>();
 bc=new ArrayList<>();
 bc.add(new float[]{318,170,0,0});
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
		//body drawing---------------------------------------------------------------------------------
		for(int i=0;i<bc.size();i++){
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
			//head coordinates and moviement effect------------------------------------------------------
		if(angle==0){
			x+=Gdx.graphics.getDeltaTime()*200;
			if (Gdx.input.isKeyPressed(Input.Keys.UP)){
				angle=90;
				al.add( new float[]{x,y,angle});
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				angle=270;
				al.add( new float[]{x,y,angle});
			}
		} else if (angle==90) {
			y+=Gdx.graphics.getDeltaTime()*200;
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				angle=180;
				al.add( new float[]{x,y,angle});
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				angle=0;
				al.add( new float[]{x,y,angle});
			}
		}
		else if (angle==180) {
			x-=Gdx.graphics.getDeltaTime()*200;
			if (Gdx.input.isKeyPressed(Input.Keys.UP)){
				angle=90;
				al.add( new float[]{x,y,angle});
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
				angle=270;
				al.add( new float[]{x,y,angle});
			}
		}
		else if (angle==270) {
			y-=Gdx.graphics.getDeltaTime()*200;
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
				angle=180;
				al.add( new float[]{x,y,angle});
			}
			else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
				angle=0;
				al.add( new float[]{x,y,angle});
			}
		}
		//body-coordinates------------------------------------------------------------------------
			for(int co=0;co<bc.size();co++){
			if(bc.get(co)[2]==0){
				bc.get(co)[0]+=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0&&bc.get(co)[3]<al.size()) {
					if (al.get((int)bc.get(co)[3])[0]<=bc.get(co)[0]){
						bc.get(co)[2]=al.get((int)bc.get(co)[3])[2];
						bc.get(co)[3]=bc.get(co)[3]+1;
					}
				}
			}
			else if (bc.get(co)[2]==180) {
				bc.get(co)[0]-=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0&&bc.get(co)[3]<al.size()) {
					if (al.get((int)bc.get(co)[3])[0]>=bc.get(co)[0]){
						bc.get(co)[2]=al.get((int)bc.get(co)[3])[2];
						bc.get(co)[3]=bc.get(co)[3]+1;
					}
				}
			}
			else if (bc.get(co)[2]==270) {
				bc.get(co)[1]-=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0&&bc.get(co)[3]<al.size()) {
					if (al.get((int)bc.get(co)[3])[1]>=bc.get(co)[1]){
						bc.get(co)[2]=al.get((int)bc.get(co)[3])[2];
						bc.get(co)[3]=bc.get(co)[3]+1;
					}
				}
			}
			else if (bc.get(co)[2]==90) {
				bc.get(co)[1]+=Gdx.graphics.getDeltaTime()*200;
				if(al.size()!=0&&bc.get(co)[3]<al.size()) {
					if (al.get((int)bc.get(co)[3])[1]<=bc.get(co)[1]){
						bc.get(co)[2]=al.get((int)bc.get(co)[3])[2];
						bc.get(co)[3]=bc.get(co)[3]+1;
					}
				}
			}
		}

		}
		if (x>=foodco.get(0)[0]&&x<=foodco.get(0)[0]+32 &&y>=foodco.get(0)[1]&&y<=foodco.get(0)[1]+32 ){
			foodco.remove(0);
if (bc.get(bc.size()-1)[2]==0){
	bc.add( new float[]{bc.get(bc.size()-1)[0]-32,bc.get(bc.size()-1)[1],0,bc.get(bc.size()-1)[3]});
} else  if (bc.get(bc.size()-1)[2]==90){
	bc.add( new float[]{bc.get(bc.size()-1)[0],bc.get(bc.size()-1)[1]-32,90,bc.get(bc.size()-1)[3]});
}else  if (bc.get(bc.size()-1)[2]==180){
	bc.add( new float[]{bc.get(bc.size()-1)[0]+32,bc.get(bc.size()-1)[1],180,bc.get(bc.size()-1)[3]});
}else  if (bc.get(bc.size()-1)[2]==270){
	bc.add( new float[]{bc.get(bc.size()-1)[0],bc.get(bc.size()-1)[1]+32,270,bc.get(bc.size()-1)[3]});
}
}
		if(x>=Gdx.graphics.getWidth()||x<0){
			start=false;
		al.clear();
			foodco.add(new int[]{r.nextInt(0,700),r.nextInt(0,330)});
			foodco.add(new int[]{r.nextInt(0,700),r.nextInt(0,330)});
			bc.clear();
			bc.add(new float[]{318,170,0,0});
		}
		if(y>=Gdx.graphics.getHeight()||y<0){
			start=false;
		al.clear();
			foodco.add(new int[]{r.nextInt(0,700),r.nextInt(0,330)});
			foodco.add(new int[]{r.nextInt(0,700),r.nextInt(0,330)});
			bc.clear();
			bc.add(new float[]{318,170,0,0});
		}
	}
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}