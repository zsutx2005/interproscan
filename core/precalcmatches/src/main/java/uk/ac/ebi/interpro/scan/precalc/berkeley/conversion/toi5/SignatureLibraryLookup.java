package uk.ac.ebi.interpro.scan.precalc.berkeley.conversion.toi5;

import uk.ac.ebi.interpro.scan.model.SignatureLibrary;

import java.util.HashMap;
import java.util.Map;

/**
 * This class cannot be instantiated.  Provides a static
 * method to lookup a SignatureLibrary object based upon a
 * name of the SignatureLibrary. Allows for any number of possible
 * synonyms.
 * TODO - currently hard coded - is it worth putting this in a config file?
 *
 * @author Phil Jones, EMBL-EBI
 * @version $Id$
 * @since 1.0
 */

public final class SignatureLibraryLookup {

    private static final Map<String, SignatureLibrary> libraryNameToSignatureLibrary = new HashMap<String, SignatureLibrary>();

    private SignatureLibraryLookup() {
    }

    static {
        // Add all the names included in the SignatureLibrary enum.
        for (SignatureLibrary sigLib : SignatureLibrary.values()) {
            libraryNameToSignatureLibrary.put(sigLib.getName().toLowerCase(), sigLib);
        }
        // And here add any other mappings, e.g. those used in Onion.
        // ALL SHOULD BE LOWER CASE. (Any queries will be converted to lower case.)
        libraryNameToSignatureLibrary.put("pfama", SignatureLibrary.PFAM);
        libraryNameToSignatureLibrary.put("pfam-a", SignatureLibrary.PFAM);
        libraryNameToSignatureLibrary.put("pfam_a", SignatureLibrary.PFAM);
        libraryNameToSignatureLibrary.put("pfam a", SignatureLibrary.PFAM);

        libraryNameToSignatureLibrary.put("pfamb", SignatureLibrary.PFAM_B);
        libraryNameToSignatureLibrary.put("pfam-b", SignatureLibrary.PFAM_B);
        libraryNameToSignatureLibrary.put("pfam b", SignatureLibrary.PFAM_B);

        libraryNameToSignatureLibrary.put("gene-3d", SignatureLibrary.GENE3D);
        libraryNameToSignatureLibrary.put("gene 3d", SignatureLibrary.GENE3D);
        libraryNameToSignatureLibrary.put("gene_3d", SignatureLibrary.GENE3D);

        libraryNameToSignatureLibrary.put("prosite_patterns", SignatureLibrary.PROSITE_PATTERNS);
        libraryNameToSignatureLibrary.put("prosite-patterns", SignatureLibrary.PROSITE_PATTERNS);
        libraryNameToSignatureLibrary.put("prosite patterns", SignatureLibrary.PROSITE_PATTERNS);

        libraryNameToSignatureLibrary.put("prosite_profiles", SignatureLibrary.PROSITE_PROFILES);
        libraryNameToSignatureLibrary.put("prosite-profiles", SignatureLibrary.PROSITE_PROFILES);
        libraryNameToSignatureLibrary.put("prosite profiles", SignatureLibrary.PROSITE_PROFILES);

        libraryNameToSignatureLibrary.put("tigrfams", SignatureLibrary.TIGRFAM);
        libraryNameToSignatureLibrary.put("tigr-fam", SignatureLibrary.TIGRFAM);
        libraryNameToSignatureLibrary.put("tigr-fams", SignatureLibrary.TIGRFAM);
        libraryNameToSignatureLibrary.put("tigr_fam", SignatureLibrary.TIGRFAM);
        libraryNameToSignatureLibrary.put("tigr_fams", SignatureLibrary.TIGRFAM);
    }

    public static SignatureLibrary lookupSignatureLibrary(String signatureLibraryName) {
        if (signatureLibraryName == null) {
            return null;
        }
        return libraryNameToSignatureLibrary.get(signatureLibraryName.toLowerCase());
    }
}
