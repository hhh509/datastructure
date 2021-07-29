public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV,double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	public Planet(Planet P){
		xxPos=P.xxPos;
		yyPos=P.yyPos;
		xxVel=P.xxVel;
		yyVel=P.yyVel;
		mass=P.mass;
		imgFileName=P.imgFileName;
	}
	public double calcDistance(Planet P){
		double xx2=(this.xxPos-P.xxPos)*(this.xxPos-P.xxPos);
		double yy2=(this.yyPos-P.yyPos)*(this.yyPos-P.yyPos);
		double dis=Math.sqrt(xx2+yy2);
		return dis;
	}
	public double calcForceExertedBy(Planet P){
		double G=6.67e-11;
		double xx2=(this.xxPos-P.xxPos)*(this.xxPos-P.xxPos);
		double yy2=(this.yyPos-P.yyPos)*(this.yyPos-P.yyPos);
		double dis=xx2+yy2;
		double force=G*this.mass*P.mass/dis;
		return force;
	}
	public double calcForceExertedByX(Planet P){
		double G=6.67e-11;
		double xx2=(this.xxPos-P.xxPos)*(this.xxPos-P.xxPos);
		double yy2=(this.yyPos-P.yyPos)*(this.yyPos-P.yyPos);
		double dis=xx2+yy2;
		double dis1=Math.sqrt(dis);
		double force=G*this.mass*P.mass/dis;
		double forcex=force*(P.xxPos-this.xxPos)/dis1;
		return forcex;
	}
	public double calcForceExertedByY(Planet P){
		double G=6.67e-11;
		double xx2=(this.xxPos-P.xxPos)*(this.xxPos-P.xxPos);
		double yy2=(this.yyPos-P.yyPos)*(this.yyPos-P.yyPos);
		double dis=xx2+yy2;
		double dis1=Math.sqrt(dis);
		double force=G*this.mass*P.mass/dis;
		double forcey=force*(P.yyPos-this.yyPos)/dis1;
		return forcey;
	}
	public double calcNetForceExertedByX(Planet[] allplanets){
		double force=0;
		for (int i=0;i<allplanets.length;i++){
			if (!this.equals(allplanets[i]))
				force+=this.calcForceExertedByX(allplanets[i]);
		}
		return force;
	}
	public double calcNetForceExertedByY(Planet[] allplanets){
		double force=0;
		for (int i=0;i<allplanets.length;i++){
			if (!this.equals(allplanets[i]))
				force+=this.calcForceExertedByY(allplanets[i]);
		}
		return force;
	}
	public void update(double dt,double fX,double fY){
		double ax=fX/this.mass;
		double ay=fY/this.mass;
		this.xxVel+=dt*ax;
		this.yyVel+=dt*ay;
		this.xxPos+=dt*this.xxVel;
		this.yyPos+=dt*this.yyVel;
	}
	public void draw(){
		String img="./images/"+this.imgFileName;
		StdDraw.picture(this.xxPos,this.yyPos,img);
	}
}