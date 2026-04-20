package modele.registry;

public enum AlgorithmRegistryExceptionType {
    DUPLICATE_IDENTIFIER,
    FAULTY_ALGORITHM;

    public String toString(){
        switch(this){
            case DUPLICATE_IDENTIFIER:
                return "un algorithm utilisant deja cette id a était enregistre";
            case FAULTY_ALGORITHM:
                return "Cette algorithm contient un erreur";
        }
        return null;
    }
}
