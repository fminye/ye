package org.gdkm.weixin.libarary.service.impl;

import java.util.LinkedList;

import org.gdkm.weixin.libarary.dao.BookRepository;
import org.gdkm.weixin.libarary.domain.Book;
import org.gdkm.weixin.libarary.domain.DebitItem;
import org.gdkm.weixin.libarary.domain.DebitList;
import org.gdkm.weixin.libarary.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class LibraryServiceImpl implements LibraryService {
	// 很多时候，DAO的名字经常改变：dao、repository
	@Autowired
	private BookRepository bookRepository;
	@Override
	public Page<Book> search(String keyword, int pageNumber) {
		Pageable pageable=PageRequest.of(pageNumber, 5);
		Page<Book> page;
		if (StringUtils.isEmpty(keyword)) {
			//没有关键字就查找所有
			page=this.bookRepository.findAll(pageable);
		}else {
			//有关键字就按照关键字查找数据
			page = this.bookRepository.findByNameContaining(keyword, pageable);	
		}
		return page;
	}
	@Override
	public void add(DebitList list, String bookId) {
		if (list.getItems() == null) {
			list.setItems(new LinkedList<>());
		}

		// 1.根据ID查询图书
		Book book = this.bookRepository.findById(bookId).get();
		// 2.判断图书是否已经在list里面存在，如果不在里面才能存储进去
		boolean exists = false;
		for (DebitItem b : list.getItems()) {
			if (b.getBook().getId().equals(bookId)) {
				// 图书已经存在
				exists = true;
			}
		}

		if (!exists) {
			DebitItem item = new DebitItem();
			item.setBook(book);
			list.getItems().add(item);
		}
	}
	@Override
	public void remove(DebitList list, String id) {
		list.getItems().stream()//
		// 过滤需要的数据，把ID相同的图书的Item返回
		.filter(item -> item.getBook().getId().equals(id))//
		// 返回找到的第一条记录
		.findFirst()//
		// 如果第一条记录存在，则从集合里面删除
		.ifPresent(item -> list.getItems().remove(item));
		
	}

	

}
