import java.util.ArrayList;
import java.awt.Image;



public class Animation {

	
		private ArrayList<OneScene> scenes;
		private int sceneIndex;
		private long movieTime;
		private long totalTime;
		private long aNum = 0;
		
		
		// constructor
		public Animation(){
			scenes = new ArrayList<OneScene>();
			totalTime = 0;
			start();
			
		}
		
		// add scene to arraylist and set time
		public synchronized void addScene(Image i, long t){
			totalTime += t;
			scenes.add(new OneScene(i, totalTime));
		}
		
		//start animation from beginning.
		public synchronized void start(){
			movieTime = 0;
			sceneIndex = 0;
		}
		
		//public synchronized void stop(){
		//	if (aNum == 1) {
		//		
		//	}
		//}
		
		//change scenes
		public synchronized void update(long timePassed){
			if(scenes.size() > 1){
				movieTime += timePassed;
				
				if(movieTime >= totalTime){
					movieTime = 0;
					sceneIndex = 0;
					aNum++;
				}
				while(movieTime > getScene(sceneIndex).endTime){
					//if (sceneIndex < scenes.size()){
						sceneIndex++; // null pointer?
					//}
					//else sceneIndex = 0;
				}
			}
		}
		
		// get animation loop number
		public long getANum(){
			return aNum;
		}
		
		// set animation loop number
		public void setANum(long newANum){
			aNum = newANum;
		}
		
		//get animations current scene( or image)
		public synchronized Image getImage(){
			if(scenes.size() == 0){
				return null;
			}
			else{
				return getScene(sceneIndex).pic;
			}
		}
		
	
		// get scene
		private OneScene getScene (int x){
			return (OneScene) scenes.get(x);
		}
		
		// private INNER CLASS ************************
		
		private class OneScene{
			Image pic;
			long endTime;
		
			public OneScene(Image pic, long endTime){
				this.pic = pic;
				this.endTime = endTime;
			}
			
		}
}
