package org.gdkm.weixin.libarary.service;

import org.gdkm.weixin.libarary.domain.Book;
import org.gdkm.weixin.libarary.domain.DebitList;
import org.springframework.data.domain.Page;

public interface LibraryService {
Page<Book> search(String keyword,int pageNumber);
void add(DebitList list, String bookId);

void remove(DebitList list, String id);
}
