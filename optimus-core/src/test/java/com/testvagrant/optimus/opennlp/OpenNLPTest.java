package com.testvagrant.optimus.opennlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;
import opennlp.tools.util.TrainingParameters;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;

public class OpenNLPTest extends OpenNLPTestBase {

//    public static void main(String[] args) throws IOException {
//        OpenNLPTest openNLPTest = new OpenNLPTest();
//        openNLPTest.sentenceTest();
//    }
    @Test
    public void sentenceTest() throws IOException {
        InputStream stream = this.getClass().getResourceAsStream("/opennlp/en-sent.bin");
        SentenceModel sentenceModel = new SentenceModel(stream);
        SentenceDetectorME sentenceDetectorME = new SentenceDetectorME(sentenceModel);
        String[] strings = sentenceDetectorME.sentDetect(getSentence6());
//        Arrays.stream(strings).forEach(System.out::println);
//        System.out.println(strings[0]);
        TokenizerModel tokenizerModel = new TokenizerModel(getClass().getResourceAsStream("/opennlp/en-token.bin"));
//        Tokenizer tokenizer = new TokenizerME(tokenizerModel);
        Tokenizer tokenizer = WhitespaceTokenizer.INSTANCE;
        String[] tokenize = tokenizer.tokenize(strings[0]);
//        String[] tokenize = new String[]{"John","Mike","Krishna"};
//        Arrays.stream(tokenize).forEach(System.out::println);
        TokenNameFinderModel model = new TokenNameFinderModel(getClass().getResourceAsStream("/opennlp/en-exceptions.bin"));
         NameFinderME nameFinderME = new NameFinderME(model);
        String[] exceptions = {"TimeoutException",":","Timed","out"};
        String[] exceptions1 = {"TimeoutException",":","Timed","out"};
        Span[] spen = nameFinderME.find(tokenize);
        Arrays.stream(spen).forEach(span -> {
            System.out.println(span);
            System.out.println(span.getType());
            System.out.println(tokenize[span.getStart()]);
        });
    }


//    @Test
    public void createTrainingData() throws IOException {
        Charset charset = Charset.forName("UTF-8");
        ObjectStream<String> lineStream =
                new PlainTextByLineStream(this.getClass().getResourceAsStream("/dataset/ExceptionsDataset.train"), charset);
        ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);

        TokenNameFinderModel model;

        try {
            model = NameFinderME.train("en", "Exceptions", sampleStream, TrainingParameters.defaultParams(),
                    (byte[]) null, Collections.<String, Object>emptyMap());
        }
        finally {
            sampleStream.close();
        }
        BufferedOutputStream modelOut = null;
        try {
            modelOut = new BufferedOutputStream(new FileOutputStream(new File("src/test/resources/opennlp/en-exceptions.bin")));
            model.serialize(modelOut);
        } finally {
            if (modelOut != null)
                modelOut.close();
        }
    }




}
