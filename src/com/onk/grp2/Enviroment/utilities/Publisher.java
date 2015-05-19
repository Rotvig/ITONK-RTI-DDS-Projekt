package com.onk.grp2.Enviroment.utilities;

import java.io.Closeable;
import java.io.IOException;

import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.topic.Topic;

public enum Publisher implements Closeable {
	INSTANCE;

	private DomainParticipant participant;
	private com.rti.dds.publication.Publisher publisher;
	private Topic topic;
	private WindTurbineDataWriter writer;
	private boolean initialized = false;

	private Publisher() {

	}

	public void Initialize(int domainId, String topicName) {
		if (!initialized) {
			createParticipant(domainId);
			createPublisher();
			createTopic(topicName);
			createDataWriter();
			initialized = true;
		}
	}

	private void createDataWriter() {
		writer = (WindTurbineDataWriter) publisher.create_datawriter(topic,
				com.rti.dds.publication.Publisher.DATAWRITER_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);

	}

	private void createTopic(String topicName) {
		String typeName = WindTurbineTypeSupport.get_type_name();
		WindTurbineTypeSupport.register_type(participant, typeName);
		this.topic = participant.create_topic(topicName, typeName,
				DomainParticipant.TOPIC_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);

	}

	private void createParticipant(int domainId) {
		participant = DomainParticipantFactory.TheParticipantFactory
				.create_participant(domainId,
						DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT, null,
						StatusKind.STATUS_MASK_NONE);
	}

	private void createPublisher() {
		publisher = participant.create_publisher(
				DomainParticipant.PUBLISHER_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);
	}

	@Override
	public void close() throws IOException {
		participant.delete_contained_entities();
		DomainParticipantFactory.TheParticipantFactory
				.delete_participant(participant);
		DomainParticipantFactory.finalize_instance();

	}

	public void notify(WindTurbine windTurbineStatus) {
		if (initialized) {
			InstanceHandle_t instanceHandle = InstanceHandle_t.HANDLE_NIL;
			writer.write(windTurbineStatus, instanceHandle);
		}

		else {
			throw new IllegalStateException(
					"Publisher hasn't been initialized. Please call the initialize method");
		}
	}
}
