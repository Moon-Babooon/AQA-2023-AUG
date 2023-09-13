CREATE TABLE Persons (
                         PersonID int AUTO_INCREMENT,
                         FirstName varchar(255),
                         LastName varchar(255),
                         Gender varchar(255),
                         Title varchar(255),
                         CONSTRAINT PRIMARY KEY (PersonID)
);

select * from Persons;

insert into Persons (FirstName, LastName, Gender, Title) VALUES
    ('John', 'Doe', 'male', 'Mr');
insert into Persons (FirstName, LastName, Gender, Title) VALUES
    ('Jane', 'Doe', 'female', 'Mrs');