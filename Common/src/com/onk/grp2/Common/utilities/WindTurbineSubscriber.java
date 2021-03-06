package com.onk.grp2.Common.utilities;

import java.io.Closeable;
import java.io.IOException;

import com.onk.grp2.Common.autoGenerated.WindTurbine;
import com.onk.grp2.Common.autoGenerated.WindTurbineDataReader;
import com.onk.grp2.Common.autoGenerated.WindTurbineSeq;
import com.onk.grp2.Common.autoGenerated.WindTurbineTypeSupport;
import com.rti.dds.domain.DomainParticipant;
import com.rti.dds.domain.DomainParticipantFactory;
import com.rti.dds.infrastructure.RETCODE_NO_DATA;
import com.rti.dds.infrastructure.ResourceLimitsQosPolicy;
import com.rti.dds.infrastructure.StatusKind;
import com.rti.dds.subscription.DataReader;
import com.rti.dds.subscription.DataReaderAdapter;
import com.rti.dds.subscription.InstanceStateKind;
import com.rti.dds.subscription.SampleInfo;
import com.rti.dds.subscription.SampleInfoSeq;
import com.rti.dds.subscription.SampleStateKind;
import com.rti.dds.subscription.ViewStateKind;
import com.rti.dds.topic.Topic;

public enum WindTurbineSubscriber implements Closeable {
	INSTANCE;

	private DomainParticipant participant;
	private com.rti.dds.subscription.Subscriber subscriber;
	private Topic topic;
	private boolean initialized = false;
	private WindTurbineReceiver receiver = null;

	private WindTurbineSubscriber() {

	}

	public void Initialize(String topicName, int domainId, WindTurbineReceiver receiver) {
		if (!initialized) {
			if(receiver == null) {
				throw new IllegalArgumentException("Specify a valid WindTurbineReceiver");
			}
			
			this.receiver = receiver;
			this.participant = createParticipant(domainId);
			this.subscriber = createSubscriber();
			this.topic = createTopic(topicName);
			createDataReader();
		}

	}

	private DomainParticipant createParticipant(int domainId) {
		return DomainParticipantFactory.TheParticipantFactory
				.create_participant(domainId,
						DomainParticipantFactory.PARTICIPANT_QOS_DEFAULT, null,
						StatusKind.STATUS_MASK_NONE);
	}

	private com.rti.dds.subscription.Subscriber createSubscriber() {
		return participant.create_subscriber(
				DomainParticipant.SUBSCRIBER_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);
	}

	private Topic createTopic(String topicName) {
		String typeName = WindTurbineTypeSupport.get_type_name();
		WindTurbineTypeSupport.register_type(participant, typeName);
		return participant.create_topic(topicName, typeName,
				DomainParticipant.TOPIC_QOS_DEFAULT, null,
				StatusKind.STATUS_MASK_NONE);

	}

	private void createDataReader() {
		
		Listener listener = new Listener();
		listener.receiver = receiver;
		
		subscriber.create_datareader(topic,
				com.rti.dds.subscription.Subscriber.DATAREADER_QOS_DEFAULT,
				listener, StatusKind.STATUS_MASK_ALL);
	}

	// TODO data listener
	private static class Listener extends DataReaderAdapter {
		WindTurbineSeq dataSeq = new WindTurbineSeq();
		SampleInfoSeq infoSeq = new SampleInfoSeq();
		WindTurbineReceiver receiver = null;

		@Override
		public void on_data_available(DataReader reader) {
			WindTurbineDataReader windTurbineReader = (WindTurbineDataReader) reader;

			try {
				windTurbineReader.take(dataSeq, infoSeq,
						ResourceLimitsQosPolicy.LENGTH_UNLIMITED,
						SampleStateKind.ANY_SAMPLE_STATE,
						ViewStateKind.ANY_VIEW_STATE,
						InstanceStateKind.ANY_INSTANCE_STATE);

				for (int i = 0; i < dataSeq.size(); i++) {
					SampleInfo info = (SampleInfo) infoSeq.get(i);
					if (info.valid_data) {
						receiver.updateWindTurbineData((WindTurbine) dataSeq.get(i));
					}

				}

			} catch (RETCODE_NO_DATA noData) {
				System.out.println("No Data was received. Error: "
						+ noData.getMessage());

			} finally {
				windTurbineReader.return_loan(dataSeq, infoSeq);
			}
		}

	}

	@Override
	public void close() throws IOException {
		if (participant != null) {
			participant.delete_contained_entities();
			DomainParticipantFactory.TheParticipantFactory
					.delete_participant(participant);
		}
	}
}