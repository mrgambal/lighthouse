import sbt._

object Resolvers {
  lazy val datamindedSnapshots = "Sonatype Nexus Repository Manager" at s"https://nexus.goodyear.eu/repository/maven-releases/"
  lazy val datamindedCredentials = Credentials(Path.userHome / ".sbt" / "nexus_creds")
}
