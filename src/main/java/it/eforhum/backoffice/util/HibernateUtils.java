package it.eforhum.backoffice.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {

	private static final Logger log = LogManager.getLogger(HibernateUtils.class);

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			
			log.debug("Try to build SessionFactory");

			try {

				BootstrapServiceRegistry bootstrapRegistry = new BootstrapServiceRegistryBuilder().build();

				// Create registry
				registry = new StandardServiceRegistryBuilder().configure().build();

				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);

				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();

				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();

				log.info("SessionFactory successfully created");
				
			} catch (Exception e) {

				log.warn("Error during SessionFactory creation");
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
				
				throw new IllegalStateException("Hibernate configuration error", e);
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

}
