package models;

import java.io.PrintWriter;

import douyu.mvc.Model;

@Model
public class MyModel {
	private int f1;
	private String f2;
	private MySubModel subModel;

	public void set(int f1, String f2, MySubModel subModel, PrintWriter out) {
		this.f1 = f1;
		this.f2 = f2;
		this.subModel = subModel;

		out.println("invoke MyModel.set(): subModel.getParentModel()==this : " + (subModel.getParentModel() == this));
		out.println();
	}

	public String toString() {
		return "MyModel[f1=" + f1 + ", f2=" + f2 + ", subModel=" + subModel + "]";
	}
}
