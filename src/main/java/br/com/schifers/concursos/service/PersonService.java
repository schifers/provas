package br.com.schifers.concursos.service;

import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.schifers.concursos.dao.PersonDAO;
import br.com.schifers.concursos.dao.PersonRoleDAO;
import br.com.schifers.concursos.dao.RoleDAO;
import br.com.schifers.concursos.dto.SignupDTO;
import br.com.schifers.concursos.entity.Person;
import br.com.schifers.concursos.entity.PersonRole;
import br.com.schifers.concursos.entity.Role;
import br.com.schifers.concursos.util.EncoderUtil;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PersonService {

    @Inject
    private PersonDAO personDao;

    @Inject
    private RoleDAO roleDao;

    @Inject
    private PersonRoleDAO personRoleDao;

    @Inject
    private EncoderUtil encoder;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Person insert(SignupDTO dto) throws NoSuchAlgorithmException {
        Person person = new Person();
        person.setUsername(dto.getUsername());
        person.setPassword(encoder.encode(dto.getPassword()));
        person = personDao.insert(person);

        Role role = roleDao.findByName("ROLE_USER");

        PersonRole personRole = new PersonRole();
        personRole.setPerson(person);
        personRole.setRole(role);
        personRole = personRoleDao.insert(personRole);

        return person;
    }

}
