package modele.registry;

public class AlgorithmRegistryException extends RuntimeException {
    private final AlgorithmRegistryExceptionType type;

    public AlgorithmRegistryException(AlgorithmRegistryExceptionType type) {
        super(type.toString());
        this.type = type;
    }
}
