package com.bazaarvoice.jolt.common.pathelement;

import com.bazaarvoice.jolt.common.reference.DollarReference;
import com.bazaarvoice.jolt.common.WalkedPath;

public class DollarPathElement extends BasePathElement implements MatchablePathElement, EvaluatablePathElement {

    private final DollarReference dRef;

    public DollarPathElement( String key ) {
        super(key);

        dRef = new DollarReference( key );
    }

    @Override
    public String getCanonicalForm() {
        return dRef.getCanonicalForm();
    }

    @Override
    public String evaluate( WalkedPath walkedPath ) {
        LiteralPathElement pe = walkedPath.elementFromEnd( dRef.getPathIndex() );
        return pe.getSubKeyRef( dRef.getKeyGroup() );
    }

    @Override
    public LiteralPathElement match( String dataKey, WalkedPath walkedPath ) {
        String evaled = evaluate( walkedPath );
        return new LiteralPathElement( evaled );
    }
}