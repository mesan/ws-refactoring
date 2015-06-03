package no.support.batch.purring;

import java.util.concurrent.atomic.AtomicInteger;

import no.support.batch.DocumentTable;


/**
 * Holder orden på tellere, trådstyring og rapportering av endelig resultat
 * for kjøring av statusjobben.
 */
public class PurreJobbResultat extends no.support.batch.BatchResult {
    // Tellerne benytter AtomicInteger for å slippe synkronisering.


    /** Antall steg totalt. */
    public static final int ANTALL_STEG= 2+1;

    /** Antall saker berørt. */
    private final AtomicInteger antallSaker= new AtomicInteger(0);

    // Tellerne benytter AtomicInteger for å slippe synkronisering.

    /**
     * Default constructor.
     * @param debug Debugmodus?
     */
    public PurreJobbResultat(final boolean debug) {
        super(ANTALL_STEG, debug);
    }


    /**
     * Oppdater antall saker berørt.
     *
     * @param antSaker Antall nye saker
     * @return Antall saker totalt
     */
    public int addSaker(final int antSaker) {
        return this.antallSaker.addAndGet(antSaker);
    }

    /**
     * Hent antall saker.
     *
     * @return Antall
     */
    public int getSaker() {
        return this.antallSaker.get();
    }

    @Override
    protected void addLocalResult(final DocumentTable<String> resultatTabell) {
        resultatTabell.addLine("Antall saker",
                               TOM_STRENG + this.antallSaker,
                               TOM_STRENG,
                               TOM_STRENG);
    }
}