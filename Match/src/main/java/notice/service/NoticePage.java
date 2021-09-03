package notice.service;

import java.util.List;

import notice.model.Notice;

public class NoticePage {
	private int total;
	private int currentPage;
	private List<Notice> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public NoticePage(int total, int currentPage, int size, List<Notice>content) {
		this.total = total;
		this.currentPage = currentPage;
		this.content = content;
		if(total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		}else {
			totalPages = total / size;
			if(total % size > 0	) {
				totalPages++;
			}
			int modVal = currentPage % 5;
			startPage = currentPage/5 * 5 + 1;
			if(modVal == 0) startPage -=5;
			
			endPage = startPage + 4;
			if(endPage > totalPages) endPage = totalPages;
		}
	}
	
	public boolean hasNoNotices() {
		return total == 0;
	}
	
	public boolean hasNotices() {
		return total>0;
	}
	
	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Notice> getContent() {
		return content;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
}
