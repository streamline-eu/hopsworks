
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.bbc.project.Consents;
import se.kth.bbc.project.privacy.model.Consent;
import se.kth.kthfsdashboard.user.AbstractFacade;

@Stateless
public class ConsentsFacade extends AbstractFacade<Consents> {

  private static final Logger logger = Logger.getLogger(ConsentsFacade.class.
      getName());

  @PersistenceContext(unitName = "kthfsPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public ConsentsFacade() {
    super(Consents.class);
  }

  public void persistConsent(Consent consent) {
    em.persist(consent);
  }  
  
}
