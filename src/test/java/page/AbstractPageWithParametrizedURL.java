package page;

public abstract class AbstractPageWithParametrizedURL extends AbstarctPage{

    protected AbstractPageWithParametrizedURL() throws Throwable {
        super();
    }

    protected abstract AbstarctPage openPage(String urlPart);
}
