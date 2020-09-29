package com.uberish.user.service;

import com.uberish.user.client.car.CarsClient;
import com.uberish.user.client.trip.TripsClient;
import com.uberish.user.exception.UserException;
import com.uberish.user.model.Car;
import com.uberish.user.model.entity.User;
import com.uberish.user.model.trip.Trip;
import com.uberish.user.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private CarsClient carsClient;

    @Mock
    private TripsClient tripsClient;

    private UsersService usersService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        usersService = new UsersService(usersRepository, carsClient, tripsClient);
    }

    @Test
    public void testFindAll() {
        //GIVEN
        List<User> expectedUsers = Collections.emptyList();

        //WHEN
        when(usersRepository.findAll()).thenReturn(expectedUsers);

        //ACTION
        List<User> actualUsers = usersService.findAll();

        //THEN
        verify(usersRepository).findAll();
        verifyNoMoreInteractions(usersRepository);
        assertEquals(expectedUsers, actualUsers);
    }

    @Test(expected = UserException.class)
    public void testFindById_userNotFound() {
        //GIVEN
        UUID userId = UUID.randomUUID();
        Optional<User> expectedUser = Optional.empty();

        //WHEN
        when(usersRepository.findById(userId)).thenReturn(expectedUser);

        //ACTION
        usersService.findById(userId);

        //THEN
        verify(usersRepository).findById(userId);
        verifyNoMoreInteractions(usersRepository);
    }

    @Test
    public void testFindById_userFound() {
        //GIVEN
        UUID userId = UUID.randomUUID();
        User expectedUser = new User();
        Optional<User> expectedUserOptional = Optional.of(new User());

        //WHEN
        when(usersRepository.findById(userId)).thenReturn(expectedUserOptional);

        //ACTION
        User actualUser = usersService.findById(userId);

        //THEN
        verify(usersRepository).findById(userId);
        verifyNoMoreInteractions(usersRepository);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testSave() {
        //GIVEN
        User expectedUser = new User();

        //WHEN
        when(usersRepository.save(expectedUser)).thenReturn(expectedUser);

        //ACTION
        User actualUser = usersService.save(expectedUser);

        //THEN
        verify(usersRepository).save(expectedUser);
        verifyNoMoreInteractions(usersRepository);
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testUpdate() {
        //GIVEN
        UUID id = UUID.randomUUID();
        User updatedUser = mock(User.class);
        User expectedUser = User.builder().id(id).build();

        //WHEN
        when(usersRepository.findById(id)).thenReturn(Optional.of(expectedUser));
        when(usersRepository.save(expectedUser)).thenReturn(expectedUser);
        when(updatedUser.getFirstName()).thenReturn("fn");
        when(updatedUser.getLastName()).thenReturn("ln");
        when(updatedUser.getPhoneNumber()).thenReturn("ph");

        //ACTION
        User actualUser = usersService.update(updatedUser, id);

        //THEN
        verify(updatedUser).getFirstName();
        verify(updatedUser).getLastName();
        verify(updatedUser).getPhoneNumber();
        verifyNoMoreInteractions(updatedUser);

        verify(usersRepository).findById(id);
        verify(usersRepository).save(expectedUser);
        verifyNoMoreInteractions(usersRepository);

        assertEquals(expectedUser, actualUser);
    }

    @Test(expected = UserException.class)
    public void testUpdate_userNotFound() {
        //GIVEN
        UUID id = UUID.randomUUID();

        //WHEN
        when(usersRepository.findById(id)).thenReturn(Optional.empty());

        //ACTION
        usersService.update(new User(), id);
    }

    @Test
    public void testDelete() {
        //GIVEN
        UUID id = UUID.randomUUID();

        //ACTION
        usersService.delete(id);

        //THEN
        verify(usersRepository).deleteById(id);
    }

    @Test
    public void testFindAllCarsByUserId() {
        //GIVEN
        UUID id = UUID.randomUUID();

        //ACTION
        usersService.findAllCarsByUserId(id);

        //THEN
        verify(carsClient).findAllByDriverId(id);
        verifyNoMoreInteractions(carsClient);
    }

    @Test
    public void testCreateCarForUser() {
        //GIVEN
        UUID id = UUID.randomUUID();
        Car expectedCar = Car.builder().id(id).build();

        //WHEN
        when(carsClient.create(expectedCar)).thenReturn(expectedCar);

        //ACTION
        Car actualCar = usersService.createCarForUser(id, expectedCar);

        //THEN
        ArgumentCaptor<Car> carArgumentCaptor = ArgumentCaptor.forClass(Car.class);
        verify(carsClient).create(carArgumentCaptor.capture());
        verifyNoMoreInteractions(carsClient);

        Car carToCreate = carArgumentCaptor.getValue();
        assertEquals(id, carToCreate.getDriverId());

        assertEquals(expectedCar, actualCar);
    }

    @Test
    public void testFindAllTripsByUserId() {
        //GIVEN
        UUID id = UUID.randomUUID();
        List<Trip> expectedTrips = Collections.emptyList();

        //WHEN
        when(tripsClient.findAllByDriverId(id)).thenReturn(expectedTrips);

        //ACTION
        List<Trip> actualTrips = tripsClient.findAllByDriverId(id);

        //THEN
        verify(tripsClient).findAllByDriverId(id);
        verifyNoMoreInteractions(tripsClient);

        assertEquals(expectedTrips, actualTrips);
    }
}
