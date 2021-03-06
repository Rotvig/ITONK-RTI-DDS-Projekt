package com.onk.grp2.Common.utilities;

import java.io.Closeable;
import java.io.IOException;

import com.onk.grp2.Common.autoGenerated.Environment;
import com.onk.grp2.Common.autoGenerated.EnvironmentDataWriter;
import com.onk.grp2.Common.autoGenerated.EnvironmentTypeSupport;
import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.InstanceHandle_t;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.topic.Topic;

/**
 * The class is used as a wrapper for setting up a publisher for RTI NDDS
 *
 * The class is created as a singleton to minimize mistakes concerning wrong
 * ID's or topic names
 *
 * @author Sune Monrad
 *
 */

public enum EnvironmentPublisher implements Closeable {
	INSTANCE;

	private DomainParticipant participant;
	private com.rti.dds.publication.Publisher publisher;
	private Topic topic;
	private EnvironmentDataWriter writer;
	private boolean initialized = false;

	private EnvironmentPublisher() {

	}

	/**
	 * initialization is only possible once to ensure a valid state
	 * 
	 * @param domainId
	 * @param topicName
	 * 
	 */

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
		writer = (EnvironmentDataWriter) publisher.create_datawriter(topic,
				com.rti.dds.publication.Publisher.DATAWRITER_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);

	}

	private void createTopic(String topicName) {
		String typeName = EnvironmentTypeSupport.get_type_name();
		EnvironmentTypeSupport.register_type(participant, typeName);
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

	/**
	 * Used to send environment status to subscribers.
	 * 
	 * The Initialize method has to have been called before calling this method.
	 * 
	 * @param EnvironmentStatus
	 */
	public void notify(Environment EnvironmentStatus) {
		if (initialized) {
			InstanceHandle_t instanceHandle = InstanceHandle_t.HANDLE_NIL;
			writer.write(EnvironmentStatus, instanceHandle);
		}

		else {
			throw new IllegalStateException(
					"Publisher hasn't been initialized. Please call the initialize method");
		}
	}
}
