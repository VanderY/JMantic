module JMantic.main {
    requires com.fasterxml.jackson.annotation;
    requires org.slf4j;
    requires com.fasterxml.jackson.databind;

    exports org.ostis.api.context;
    exports org.ostis.scmemory.websocketmemory.sync;

    exports org.ostis.scmemory.model;
    exports org.ostis.scmemory.model.exception;
    exports org.ostis.scmemory.model.event;
    exports org.ostis.scmemory.model.element;
    exports org.ostis.scmemory.model.element.node;
    exports org.ostis.scmemory.model.element.link;
    exports org.ostis.scmemory.model.element.edge;
    exports org.ostis.scmemory.model.pattern;
    exports org.ostis.scmemory.model.pattern.factory;
    exports org.ostis.scmemory.model.pattern.pattern3;
    exports org.ostis.scmemory.model.pattern.pattern5;

}