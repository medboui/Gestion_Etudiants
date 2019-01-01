package com.me;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.me.dao.EtudiantRepository;
import com.me.entities.Etudiant;

@SpringBootApplication
public class EtudiantsApplication {

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx= SpringApplication.run(EtudiantsApplication.class, args);
		EtudiantRepository etudiantRepository=ctx.getBean(EtudiantRepository.class);

		DateFormat df=new SimpleDateFormat("dd/mm/yyyy");
		etudiantRepository.save(new Etudiant("yossra",df.parse("07/07/2012"),"med@gmail.com","1.jpg","1.pdf"));
		etudiantRepository.save(new Etudiant("mohamed",df.parse("07/07/2013"),"med@gmail.com","2.jpg","1.pdf"));
		etudiantRepository.save(new Etudiant("brahim",df.parse("07/07/2012"),"med@gmail.com","3.jpg","1.pdf"));

		 Page<Etudiant> etds=etudiantRepository.findAll(new PageRequest(0, 5));
		 etds.forEach(c->{
	    	   System.out.println(c.getNom());
	       
	       });
		 List<Etudiant> ets= etudiantRepository.chercherBydate(df.parse("07/07/2012"));
		 ets.forEach(c->{
	    	   System.out.println(c.getNom());
		       
	       });
	
	}
}
