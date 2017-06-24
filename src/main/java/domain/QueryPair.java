package domain;

public class QueryPair<T,U> {
	public T lft;
	public U rgt;
	
	public QueryPair(T lft, U rgt){
		this.lft = lft;
		this.rgt = rgt;
	}
	
	public void setLft(T lft){
		this.lft = lft;
	}
	
	public T getLft(){
		return this.lft;
	}
	
	public void setRgt(U rgt){
		this.rgt = rgt;
	}
	
	public U getRgt(){
		return this.rgt;
	}
	
}
