package jp.ac.kic.st.s15018;

public class BookBean {
	private String name;
	private String author;
	private String isbn;
	private int publishYear;
	private boolean kashidashi;

	public BookBean(){ //デフォルトの引数なしのコンストラクタ
		this.name = "";
		this.author = "";
		this.isbn = "";
		this.publishYear = 0;
		this.kashidashi = false;
	}

	public BookBean(String name, String author, String isbn, int publishYear, Boolean kashidashi){
		this.name = name;
		this.author = author;
		this.isbn = isbn;
		this.publishYear = publishYear;
		this.kashidashi = kashidashi;
	}

	public BookBean(String name, String author, String isbn, int publishYear){
		this(name,author,isbn,publishYear,false);
	}

	public String getName(){
		return this.name;
	}

	public String getAuthor(){
		return this.author;
	}

	public String getIsbn(){
		return this.isbn;
	}

	public int getPublishYear(){
		return this.publishYear;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public void setIsbn(String isbn){
		this.isbn = isbn;
	}

	public void setPublishYear(int publishYear){
		this.publishYear = publishYear;
	}

	public void setKashidashi(boolean kashidashi){
		this.kashidashi = kashidashi;
	}

	public boolean getKashidashi(){
		return this.kashidashi;
	}

	public String getKashidashiString(){
		String retval;
		if(this.kashidashi) retval = "貸出中";
		else retval ="返却";
		return retval;
	}

	@Override
	public boolean equals(Object obj){
		if(obj == this) return true;
		if(obj == null) return false;
		if(!(obj instanceof BookBean)) return false;
		BookBean bean = (BookBean) obj;
		if(this.getName().equals(bean.getName())) return true;
		return false;
	}

	@Override
	public int hashCode(){
		int retval = this.name.hashCode();
		retval += this.author.hashCode();
		retval += this.isbn.hashCode();
		retval += this.publishYear * 51;
		return retval;
	}

}
