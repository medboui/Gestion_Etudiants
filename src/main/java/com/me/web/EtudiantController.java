package com.me.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
//import org.apache.tomcat.util.http.fileupload.IOUtils;     attention ne pas utiliser cet import 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.me.dao.EtudiantRepository;
import com.me.entities.Etudiant;

@Controller
@RequestMapping(value="/Etudiant")
public class EtudiantController {
	@Autowired
	private EtudiantRepository etudiantRepository;
	@Value("${dir.images}")
	private String imageDir;
	@Value("${dir.files}")
	private String fileDir;

	@RequestMapping(value="/Index")
	public String Index(Model model,@RequestParam(name="page",defaultValue="0") int p,
			@RequestParam(name="motCle",defaultValue="") String mc){
		Page<Etudiant> pageEtudiants=etudiantRepository.chercherEtudiants("%"+mc+"%",new PageRequest(p,5));
		int pagesCount=pageEtudiants.getTotalPages();
		int[]pages=new int[pagesCount];
		for (int i=0;i<pagesCount;i++){
			pages[i]=i;
		}
		model.addAttribute("pages",pages);
		model.addAttribute("pageEtudiants", pageEtudiants);
		model.addAttribute("pageCourante",p);
		model.addAttribute("motCle",mc);
		
		return "etudiants";
	}
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String formEtudiant(Model model){
		model.addAttribute("etudiant",new Etudiant());
		return "FormEtudiant";
	}
	@RequestMapping(value="/SaveEtudiant",method=RequestMethod.POST)
	public String save(@Valid Etudiant et,BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile file,@RequestParam(name="file")MultipartFile insFile) throws IllegalStateException, IOException{			//on effectue la validation by using Valid et si on des messages 
				if (bindingResult.hasErrors()){					//on récupére le fichier image by using MultipartFile 
					return "FormEtudiant";
				}
				if (!(file.isEmpty())){
					et.setPhoto(file.getOriginalFilename());
				}
				etudiantRepository.save(et);
		if (!(file.isEmpty())){
			et.setPhoto(file.getOriginalFilename()); //permet de retourner le nom original du fichier
			//file.transferTo(new File(System.getProperty("user.home")+"Desktop/java/scolarity/"+file.getOriginalFilename()));
		
			file.transferTo(new File(imageDir+et.getId()));
		}
		if (!(insFile.isEmpty())){
			et.setFile(insFile.getOriginalFilename());
		}
		
if (!(insFile.isEmpty())){
	et.setFile(insFile.getOriginalFilename()); //permet de retourner le nom original du fichier
	//file.transferTo(new File(System.getProperty("user.home")+"Desktop/java/scolarity/"+file.getOriginalFilename()));
	etudiantRepository.save(et);
	insFile.transferTo(new File(fileDir+et.getId()));
}
		
		return "redirect:/Etudiant/Index"; //ou encore return "redirect:Index"
	}
	@RequestMapping(value="/getPhoto",produces=MediaType.IMAGE_JPEG_VALUE) //je vais envoyer la photo sous forme JPG 
	@ResponseBody //on dit à lui qui ce que va etre retourné par cette methode doit etre envoyé au body de la vue
	public byte[] getPhoto(Long id ) throws FileNotFoundException, IOException{
		File f=new File(imageDir+id);
		byte[] bytes = IOUtils.toByteArray(new FileInputStream(f));
		return bytes ;
	}
	
	@RequestMapping(value="/getFile",produces=MediaType.APPLICATION_PDF_VALUE) //je vais envoyer la photo sous forme JPG 
	@ResponseBody //on dit à lui qui ce que va etre retourné par cette methode doit etre envoyé au body de la vue
	public byte[] getFile(Long id ) throws FileNotFoundException, IOException{
		File f=new File(fileDir+id);
		byte[] bytes = IOUtils.toByteArray(new FileInputStream(f));
		return bytes ;
	}
	
	@RequestMapping(value="/supprimer")
	public String supprimer(Long id){
		etudiantRepository.delete(id);
		return "redirect:Index";
	}
	@RequestMapping(value="/edit")
	public String edit(Long id,Model model){
		Etudiant  et= etudiantRepository.getOne(id);
		model.addAttribute("etudiant",et);
		return "EditEtudiant";
		
	}
	
	@RequestMapping(value="/UpdateEtudiant",method=RequestMethod.POST)
	public String update(@Valid Etudiant et,BindingResult bindingResult,
			@RequestParam(name="picture")MultipartFile picture,@RequestParam(name="file")MultipartFile file) throws IllegalStateException, IOException{								//on effectue la validation by using Valid et si on des messages 
				if (bindingResult.hasErrors()){					//on récupére le fichier image by using MultipartFile 
					return "EditEtudiant";
				}
				if (!(picture.isEmpty())){
					et.setPhoto(picture.getOriginalFilename());
				}
				etudiantRepository.save(et);
		if (!(picture.isEmpty())){
			et.setPhoto(picture.getOriginalFilename()); //permet de retourner le nom original du fichier
			//file.transferTo(new File(System.getProperty("user.home")+"Desktop/java/scolarity/"+file.getOriginalFilename()));
		
			picture.transferTo(new File(imageDir+et.getId()));
		}
		if (!(file.isEmpty())){
			et.setFile(file.getOriginalFilename());
		}
	
   if (!(file.isEmpty())){
	et.setFile(file.getOriginalFilename()); //permet de retourner le nom original du fichier
	//file.transferTo(new File(System.getProperty("user.home")+"Desktop/java/scolarity/"+file.getOriginalFilename()));
	etudiantRepository.save(et);
	file.transferTo(new File(fileDir+et.getId()));
}
		
		return "redirect:/Etudiant/Index"; //ou encore return "redirect:Index"
	}
}
