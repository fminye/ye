package org.gdkm.weixin.libarary.dao;


import org.gdkm.weixin.libarary.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BookRepository extends JpaRepository<Book,String>{
	// 最终: select * from Book where name like '%keyword%'
	//keyword表示关键字,pageable表示需要传入页码数
	Page<Book>findByNameContaining(String keyword,Pageable pageable);
}
