package com.shiro;

public class UpdateContext {
	private UpdateStrategy updateStrategy;
	
	public UpdateContext(UpdateStrategy updateStrategy) {
		  this.updateStrategy = updateStrategy;
	}
	public void setUpdateContext(UpdateStrategy updateStrategy) {
		 this.updateStrategy = updateStrategy;
	}
	public void performUpdate(int id,String newData) throws Exception {
		updateStrategy.Update(id,newData);
	}

}
