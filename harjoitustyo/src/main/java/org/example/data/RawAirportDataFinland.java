package org.example.data;

import java.util.ArrayList;

//CHECKSTYLE.OFF: LineLength

/**
 * Holds raw data for airports. Needed for gradle fat jar creation.
 */
public class RawAirportDataFinland implements RawAirportData {
    private final ArrayList<String> rawData;

    /**
     * The Constructor for the RawAirportData object.
     */
    public RawAirportDataFinland() {
        rawData = new ArrayList<>();

        addFinland();
    }

    private void addFinland() {
        // test airport data - Finland only

        rawData.add("417,\"Enontekio Airport\",\"Enontekio\",\"Finland\",\"ENF\",\"EFET\",68.362602233887,23.424299240112,1005,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("418,\"Eura Airport\",\"Eura\",\"Finland\",\\N,\"EFEU\",61.1161003112793,22.201400756835938,259,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("419,\"Halli Airport\",\"Halli\",\"Finland\",\"KEV\",\"EFHA\",61.856039,24.786686,479,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("420,\"Helsinki Malmi Airport\",\"Helsinki\",\"Finland\",\"HEM\",\"EFHF\",60.254600524902344,25.042800903320312,57,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("421,\"Helsinki Vantaa Airport\",\"Helsinki\",\"Finland\",\"HEL\",\"EFHK\",60.317199707031,24.963300704956,179,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("422,\"Hameenkyro Airport\",\"Hameenkyro\",\"Finland\",\\N,\"EFHM\",61.689701080322266,23.073699951171875,449,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("423,\"Hanko Airport\",\"Hanko\",\"Finland\",\\N,\"EFHN\",59.848899841308594,23.083599090576172,20,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("424,\"Hyvinkää Airfield\",\"Hyvinkaa\",\"Finland\",\"HYV\",\"EFHV\",60.6543998718,24.8810997009,430,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("425,\"Kiikala Airport\",\"Kikala\",\"Finland\",\\N,\"EFIK\",60.4625015259,23.6525001526,381,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("426,\"Immola Airport\",\"Immola\",\"Finland\",\\N,\"EFIM\",61.24919891357422,28.90369987487793,338,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("427,\"Kitee Airport\",\"Kitee\",\"Finland\",\"KTQ\",\"EFIT\",62.1661,30.073601,364,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("428,\"Ivalo Airport\",\"Ivalo\",\"Finland\",\"IVL\",\"EFIV\",68.607299804688,27.405300140381,481,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("429,\"Joensuu Airport\",\"Joensuu\",\"Finland\",\"JOE\",\"EFJO\",62.662899017334,29.607500076294,398,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("430,\"Jyvaskyla Airport\",\"Jyvaskyla\",\"Finland\",\"JYV\",\"EFJY\",62.399501800537,25.678300857544,459,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("431,\"Kauhava Airport\",\"Kauhava\",\"Finland\",\"KAU\",\"EFKA\",63.127102,23.051399,151,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("432,\"Kemi-Tornio Airport\",\"Kemi\",\"Finland\",\"KEM\",\"EFKE\",65.778701782227,24.582099914551,61,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("433,\"Kajaani Airport\",\"Kajaani\",\"Finland\",\"KAJ\",\"EFKI\",64.285499572754,27.692399978638,483,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("434,\"Kauhajoki Airport\",\"Kauhajoki\",\"Finland\",\"KHJ\",\"EFKJ\",62.4625015259,22.3931007385,407,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("435,\"Kokkola-Pietarsaari Airport\",\"Kruunupyy\",\"Finland\",\"KOK\",\"EFKK\",63.721199035645,23.143100738525,84,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("436,\"Kemijarvi Airport\",\"Kemijarvi\",\"Finland\",\\N,\"EFKM\",66.712898,27.156799,692,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("437,\"Kuusamo Airport\",\"Kuusamo\",\"Finland\",\"KAO\",\"EFKS\",65.987602233887,29.239400863647,866,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("438,\"Kittilä Airport\",\"Kittila\",\"Finland\",\"KTT\",\"EFKT\",67.700996398926,24.846799850464,644,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("439,\"Kuopio Airport\",\"Kuopio\",\"Finland\",\"KUO\",\"EFKU\",63.007099151611,27.797800064087,323,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("440,\"Lahti Vesivehmaa Airport\",\"Vesivehmaa\",\"Finland\",\"QLF\",\"EFLA\",61.144199,25.693501,502,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("441,\"Lappeenranta Airport\",\"Lappeenranta\",\"Finland\",\"LPP\",\"EFLP\",61.044601,28.144743,349,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("442,\"Mariehamn Airport\",\"Mariehamn\",\"Finland\",\"MHQ\",\"EFMA\",60.122200012207,19.898199081421,17,2,\"E\",\"Europe/Mariehamn\",\"airport\",\"OurAirports\"");
        rawData.add("443,\"Menkijarvi Airport\",\"Menkijarvi\",\"Finland\",\\N,\"EFME\",62.94670104980469,23.51889991760254,331,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("444,\"Mikkeli Airport\",\"Mikkeli\",\"Finland\",\"MIK\",\"EFMI\",61.6866,27.201799,329,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("445,\"Nummela Airport\",\"Nummela\",\"Finland\",\\N,\"EFNU\",60.333900451699996,24.2964000702,367,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("446,\"Oulu Airport\",\"Oulu\",\"Finland\",\"OUL\",\"EFOU\",64.930099487305,25.354600906372,47,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("447,\"Piikajarvi Airport\",\"Piikajarvi\",\"Finland\",\\N,\"EFPI\",61.245601654052734,22.19339942932129,148,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("448,\"Pori Airport\",\"Pori\",\"Finland\",\"POR\",\"EFPO\",61.461700439453,21.799999237061,44,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("449,\"Pudasjärvi Airport\",\"Pudasjarvi\",\"Finland\",\\N,\"EFPU\",65.4021987915,26.946899414100002,397,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("450,\"Pyhäsalmi Airport\",\"Pyhasalmi\",\"Finland\",\\N,\"EFPY\",63.7318992615,25.926300048799998,528,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("451,\"Raahe Pattijoki Airport\",\"Pattijoki\",\"Finland\",\\N,\"EFRH\",64.6881027222,24.6958007812,118,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("452,\"Rantasalmi Airport\",\"Rantasalmi\",\"Finland\",\\N,\"EFRN\",62.0654983521,28.3565006256,292,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("453,\"Rovaniemi Airport\",\"Rovaniemi\",\"Finland\",\"RVN\",\"EFRO\",66.564796447754,25.830400466919,642,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("454,\"Rayskala Airport\",\"Rayskala\",\"Finland\",\\N,\"EFRY\",60.74470138549805,24.107799530029297,407,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("455,\"Savonlinna Airport\",\"Savonlinna\",\"Finland\",\"SVL\",\"EFSA\",61.943099975586,28.945100784302,311,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("456,\"Selanpaa Airport\",\"Selanpaa\",\"Finland\",\\N,\"EFSE\",61.062400817871094,26.798900604248047,417,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("457,\"Sodankyla Airport\",\"Sodankyla\",\"Finland\",\"SOT\",\"EFSO\",67.3949966431,26.6191005707,602,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("458,\"Tampere-Pirkkala Airport\",\"Tampere\",\"Finland\",\"TMP\",\"EFTP\",61.414100646973,23.604400634766,390,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("459,\"Teisko Airport\",\"Teisko\",\"Finland\",\\N,\"EFTS\",61.7733,24.027,515,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("460,\"Turku Airport\",\"Turku\",\"Finland\",\"TKU\",\"EFTU\",60.514099121094,22.262800216675,161,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("461,\"Utti Air Base\",\"Utti\",\"Finland\",\"UTI\",\"EFUT\",60.89640045166,26.938400268555,339,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("462,\"Vaasa Airport\",\"Vaasa\",\"Finland\",\"VAA\",\"EFVA\",63.050701141357,21.762199401855,19,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("463,\"Varkaus Airport\",\"Varkaus\",\"Finland\",\"VRK\",\"EFVR\",62.171100616455,27.868600845337,286,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
        rawData.add("464,\"Ylivieska Airfield\",\"Ylivieska-raudaskyla\",\"Finland\",\"YLI\",\"EFYL\",64.0547222,24.7252778,252,2,\"E\",\"Europe/Helsinki\",\"airport\",\"OurAirports\"");
    }

    /**
     * @return The raw data for the airports.
     */
    @Override
    public ArrayList<String> getRawData() {
        return this.rawData;
    }
}
