# easyauth
# Gradle:
		implementation 'com.mahirson.easylib:easyauth:0.1'

# Maven:
		<dependency>
			<groupId>com.mahirson.easylib</groupId>
			<artifactId>easyauth</artifactId>
			<version>0.1</version>
			<type>pom</type>
		</dependency>

# Ivy:
		<dependency org='com.mahirson.easylib' name='easyauth' rev='0.1'>
			<artifact name='easyauth' ext='pom' ></artifact>
		</dependency>

# Saving user instance:
        //MyUser class must extends User class of the library    
        easyAuth.saveUser(new MyUser("token"));

# Requesting if user logged in or not:
        //hasUser method must be called when launching application
        //this method reads saved user data from memory and sets it to variable    
        if (easyAuth.hasUser()){
                    System.out.println(easyAuth.getUser());
         }

# Sample code: