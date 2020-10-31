package org.acme.resteasy;

import org.acme.entity.Gift;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/resteasy/hello")
public class ExampleResource {

    @Inject
    EntityManager entityManager;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/create/{name}")
    @Transactional
    public String create(@PathParam String name) {
        Gift gift = new Gift();
        gift.setName(name);
        entityManager.persist(gift);
        return "ok";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/read/{id}")
    @Transactional
    public String read(@PathParam Long id) {
        Gift gift;
        gift = entityManager.find(Gift.class, id);
        return gift.getName();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/update/{id}/{name}")
    @Transactional
    public String update(@PathParam Long id, @PathParam String name) {
        var gift = entityManager.find(Gift.class, id);
        gift.setName(name);
        return "ok";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/delete/{id}")
    @Transactional
    public String delete(@PathParam Long id) {
        Gift gift = entityManager.find(Gift.class, id);
        entityManager.remove(gift);
        return "ok";
    }
}