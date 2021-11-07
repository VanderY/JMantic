package org.jmantic.scmemory.websocketmemory.sync;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jmantic.scmemory.model.exception.ScMemoryException;
import org.jmantic.scmemory.websocketmemory.core.OstisClient;
import org.jmantic.scmemory.websocketmemory.message.request.*;
import org.jmantic.scmemory.websocketmemory.message.response.*;
import org.jmantic.scmemory.websocketmemory.sender.RequestSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael
 * @since 0.0.1
 */
class RequestSenderImpl implements RequestSender {
    private final static Logger logger = LoggerFactory.getLogger(RequestSenderImpl.class);
    private final OstisClient client;
    private final ObjectMapper mapper;
    private final ObjectWriter writer;

    public RequestSenderImpl(OstisClient client) {
        this.client = client;
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        writer = mapper.writer();
    }

    @Override
    public CreateScElResponse sendCreateElRequest(CreateScElRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("!!!!!!!Request to ostis - {}", request);
            String msg = client.sendToOstis(jsonRequest);
            CreateScElResponse response = mapper.readValue(msg, CreateScElResponseImpl.class);
            logger.info("!!!!!!!Response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse request - " + request;
            logger.error(msg);
            throw new ScMemoryException(msg, e);
        }
    }

    @Override
    public DeleteScElResponse sendDeleteElRequest(DeleteScElRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("!!!!!!!Request to ostis - {}", request);
            String msg = client.sendToOstis(jsonRequest);
            DeleteScElResponseImpl response = mapper.readValue(msg, DeleteScElResponseImpl.class);
            logger.info("!!!!!!!Response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "!!!!!!!cant parse request - " + request;
            logger.error(msg);
            throw new ScMemoryException(msg, e);
        }
    }

    @Override
    public SearchByTemplateResponse sendSearchByTemplateRequest(SearchByTemplateRequest request) throws ScMemoryException {
        SearchByTemplateResponse response = null;
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("!!!!!!!!!!!!Request to ostis - {}", jsonRequest);
            String msg = client.sendToOstis(jsonRequest);
            logger.info("!!!!!!!!!!!!msg - {}", msg);
            response = mapper.readValue(msg, SearchByTemplateResponseImpl.class);
            logger.info("Response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse response - " + response;
            logger.error(msg);
            throw new ScMemoryException(msg, e);
        }
    }

    @Override
    public SetLinkContentResponse sendSetLinkContentRequest(SetLinkContentRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("!!!!!!!Request to ostis - {}", request);
            String msg = client.sendToOstis(jsonRequest);
            SetLinkContentResponse response = mapper.readValue(msg, SetLinkContentResponseImpl.class);
            logger.info("!!!!!!!Response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse request - " + request;
            logger.error(msg);
            throw new ScMemoryException(msg, e);
        }
    }

    @Override
    public GetLinkContentResponse sendGetLinkContentRequest(GetLinkContentRequest request) throws ScMemoryException {
        try {
            String jsonRequest = writer.writeValueAsString(request);
            logger.info("!!!!!!!Request to ostis - {}", request);
            String msg = client.sendToOstis(jsonRequest);
            GetLinkContentResponse response = mapper.readValue(msg, GetLinkContentResponseImpl.class);
            logger.info("!!!!!!!Response from ostis - {}", response);
            return response;
        } catch (JsonProcessingException e) {
            String msg = "cant parse request - " + request;
            logger.error(msg);
            throw new ScMemoryException(msg, e);
        }
    }
}
