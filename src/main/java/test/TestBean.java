package test;


import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import test.attachment.Attachment;
import test.attachment.Content;
import test.attachment.Nomenclature;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Named("testBean")
@Scope("request")
public class TestBean {

    private String name = "test";
    @PersistenceContext
    private EntityManager em;

    private List<Attachment> attachments;
    private Part file;

    public TestBean() {
    }

    @PostConstruct
    public void init() {
        Query query = em.createQuery("select r from Attachment r");
        attachments = query.getResultList();

//        Nomenclature n = em.find(Nomenclature.class, 4);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void check(){

        Query query = em.createQuery("select r from TestEntity r");
        List<TestEntity> list = query.getResultList();

        em.merge(new TestEntity("test"));
        int a = 1;
    }

    public void errorCheck(){
        errorMethod();
    }


    public void errorMethod(){
        try{
            methodC();
        } catch (Exception e){
            System.out.println("sdf");
        }
    }


    @Transactional
    public void methodC(){
        em.persist(new TestEntity());
    }


    @Transactional
    public void upload() {
        Attachment attachment = new Attachment();
        try {
            InputStream input = file.getInputStream();
            byte[] content = new byte[(int) file.getSize()];
            input.read(content);

            Content c = new Content();
            c.setContent(content);

            attachment.setName(getFilename(file));
            attachment.setSize(file.getSize());
            attachment.setContent(c);
            attachment.setType(file.getContentType());


            Content cm = em.merge(attachment.getContent());
            attachment.setId(cm.getId());
            attachment.setContent(cm);
            attachment = em.merge(attachment);
            attachments.add(attachment);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }




    public void download(Attachment attachment){

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.responseReset();
        ec.setResponseContentType(attachment.getType());
        ec.setResponseContentLength((int) attachment.getSize());
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + attachment.getName() + "\"");

        try {
            Content content = em.find(Content.class, attachment.getId());
            OutputStream output = ec.getResponseOutputStream();
            output.write(content.getContent());
//            OutputStream output = ec.getResponseOutputStream();
//            output.write(attachment.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        fc.responseComplete();

    }


    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}
