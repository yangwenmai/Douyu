package models;

import douyu.mvc.Model;

@Model
public class MySubModel {

	private int f1;
	private String f2;
	private MyModel parentModel;

	public void set(int f1, String f2, MyModel parentModel) {
		this.f1 = f1;
		this.f2 = f2;
		this.parentModel = parentModel;
	}

	public MyModel getParentModel() {
		return parentModel;
	}

	public String toString() {
		return "MySubModel[f1=" + f1 + ", f2=" + f2 + "]";
	}
}
