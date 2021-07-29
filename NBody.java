public class NBody{
	public static double readRadius(String path){
		In in = new In(path);
		int x=in.readInt();
		double r=in.readDouble();
		return r;
	}
	public static Planet[] readPlanets(String path){
		double xxPos,yyPos,xxVel,yyVel,mass;
		String imgFileName;

		In in=new In(path);
		int length=in.readInt();
		double r=in.readDouble();
		int i=0;
		Planet[] planets=new Planet[length];
		while (i<length){
			xxPos=in.readDouble();
			yyPos=in.readDouble();
			xxVel=in.readDouble();
			yyVel=in.readDouble();
			mass=in.readDouble();
			imgFileName=in.readString();
			planets[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
			i++;
		}
		return planets;
	}
	public static void main(String[] args){
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		double t=0;
		double[] xForces=new double[5];
		double[] yForces=new double[5];
		double r=readRadius("./data/planets.txt");
		Planet[] planets=new Planet[5];
		planets=readPlanets("./data/planets.txt");
		String imageToDraw = "./images/starfield.jpg";
		for(t=0;t<=T;t+=dt){
			for (int k=0;k<5;k++){
				xForces[k]=planets[k].calcNetForceExertedByX(planets);
				yForces[k]=planets[k].calcNetForceExertedByY(planets);
				planets[k].update(dt,xForces[k],yForces[k]);
			}
			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-r,r);
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);
			StdDraw.show();

			for(int i=0;i<5;i++){
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < planets.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
				}
		
	}
}