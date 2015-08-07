package guiPanels;

public class Test {
	
	static long count = 0L;

	public static void main(String[] args) {
		while(true){
			count ++;
			System.out.println(count);
		    new Thread(new Runnable(){

				@Override
				public void run() {
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}}).start();
		}

	}

}
