package Personne;

public interface ProprietaireInterface<T> {

    public T getIdentifiant();

    public T getContact();
    
    public void setIdentifiant(T identifiant);
    
    public void setContact(T contact);
}
