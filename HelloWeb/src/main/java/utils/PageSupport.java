package utils;

public class PageSupport {
	// current pageNum   come from user input
	private int currentPageNum = 1;
	
	// 页面容量
	private int pageSize = 0;
	
	// total page number
	private int totalPageCount;

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	
}
