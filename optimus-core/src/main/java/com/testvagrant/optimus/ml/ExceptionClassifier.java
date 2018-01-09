package com.testvagrant.optimus.ml;


import com.testvagrant.optimus.builder.ExceptionEntityBuilder;
import com.testvagrant.optimus.entity.ExceptionEntity;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Optional;

import static java.util.Arrays.stream;

public class ExceptionClassifier {

    private String exception;

    public ExceptionClassifier(String exception) {
        this.exception = exception.split("\\n")[0];
    }

    public ExceptionEntity classifyException() throws IOException {
        String exceptionSentence = getExceptionSentence(exception);
        String[] sentenceTokens = getExceptionTokens(exceptionSentence);
        Span[] identifiedTokens = getIdentifiedTokens(sentenceTokens);
        ExceptionEntity exceptionEntity = buildException(sentenceTokens,identifiedTokens);
        return exceptionEntity;
    }


    private String getExceptionSentence(String exception) throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/opennlp/en-sent.bin");
        SentenceModel sentenceModel = new SentenceModel(inputStream);
        SentenceDetectorME sentenceDetectorME = new SentenceDetectorME(sentenceModel);
        String[] strings = sentenceDetectorME.sentDetect(exception);
        Optional<String> optionalExceptionSentence = stream(strings).findFirst();
        return optionalExceptionSentence.orElseThrow(RuntimeException::new);
    }


    private String[] getExceptionTokens(String exceptionKeySentence) throws IOException {
        Tokenizer tokenizer = WhitespaceTokenizer.INSTANCE;
        String[] tokenize = tokenizer.tokenize(exceptionKeySentence);
        return tokenize;
    }

    private Span[] getIdentifiedTokens(String[] tokenize) throws IOException {
        TokenNameFinderModel model = new TokenNameFinderModel(getClass().getResourceAsStream("/opennlp/en-exceptions.bin"));
        NameFinderME nameFinderME = new NameFinderME(model);
        return nameFinderME.find(tokenize);
    }

    private ExceptionEntity buildException(String[] exceptionTokens, Span[] identifiedTokens) {
        ExceptionEntityBuilder exceptionEntityBuilder = new ExceptionEntityBuilder();
        Arrays.stream(identifiedTokens).forEach(token -> {
            switch (token.getType()) {
                case "name":
                    exceptionEntityBuilder.withException(exceptionTokens[token.getStart()]);
                break;
                case "waitFor":
                    exceptionEntityBuilder.withWaitFor(exceptionTokens[token.getStart()]);
                break;
                case "locator":
                    exceptionEntityBuilder.withLocator(exceptionTokens[token.getStart()]);
                break;
                case "value":
                    exceptionEntityBuilder.withValue(exceptionTokens[token.getStart()]);
                break;
            }
        });
        return exceptionEntityBuilder.build();
    }


}
