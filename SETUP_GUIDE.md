# Setup Guide

## Prerequisites

- Java 21
- PostgreSQL
- Postman

## Install PostgreSQL

**macOS:**
```bash
brew install postgresql@14
brew services start postgresql@14
```

**Windows:**
Download from https://www.postgresql.org/download/windows/

**Linux:**
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
```

## Create Database

**macOS/Linux:**
```bash
createdb ticketing_db
```

**Windows:**
```bash
psql -U postgres
CREATE DATABASE ticketing_db;
\q
```

## Clone and Configure

```bash
git clone https://github.com/YOUR_USERNAME/event-ticketing-system.git
cd event-ticketing-system
```

Edit `demo/src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**macOS:** Use your system username, not `postgres`
**Windows:** Use `postgres` username

## Run Application

```bash
cd demo
./mvnw spring-boot:run  # macOS/Linux
mvnw.cmd spring-boot:run  # Windows
```

Wait for: `Started DemoApplication`

Server: `http://localhost:8080`

## Test APIs in Postman

**Postman Setup:**
1. Create request
2. Select method (POST/GET/PUT)
3. For POST/PUT: Body → raw → JSON
4. Paste JSON data
5. Send

**Test order:**

### 1. Create Organizer
POST `http://localhost:8080/api/organizers`
```json
{
  "name": "Starlight Events Co",
  "email": "info@starlightevents.io",
  "phone": "415-782-9456"
}
```

### 2. Create Venue
POST `http://localhost:8080/api/venues`
```json
{
  "name": "Crystal Arena",
  "address": "7842 Harmony Boulevard",
  "city": "San Francisco",
  "totalCapacity": 15000
}
```

### 3. Create Event
POST `http://localhost:8080/api/events`
```json
{
  "title": "Tech Summit 2026",
  "description": "Annual technology conference",
  "eventDate": "2026-09-22T14:30:00",
  "status": "UPCOMING",
  "organizerId": 1,
  "venueId": 1
}
```

### 4. Create Ticket (Premium)
POST `http://localhost:8080/api/ticket-types`
```json
{
  "name": "Premium Access",
  "price": 449.50,
  "quantityAvailable": 75,
  "eventId": 1
}
```

### 5. Create Ticket (Standard)
POST `http://localhost:8080/api/ticket-types`
```json
{
  "name": "Standard Entry",
  "price": 149.00,
  "quantityAvailable": 350,
  "eventId": 1
}
```

### 6. Create Ticket (Early Bird)
POST `http://localhost:8080/api/ticket-types`
```json
{
  "name": "Early Bird Special",
  "price": 89.99,
  "quantityAvailable": 125,
  "eventId": 1
}
```

### 7. Get All Events
GET `http://localhost:8080/api/events`

### 8. Get Event by ID
GET `http://localhost:8080/api/events/1`

### 9. Register Attendee
POST `http://localhost:8080/api/attendees`
```json
{
  "name": "Sarah Martinez",
  "email": "sarah.m@techmail.com"
}
```

### 10. Create Booking
POST `http://localhost:8080/api/bookings`
```json
{
  "attendeeId": 1,
  "ticketTypeId": 1
}
```

### 11. Get Revenue
GET `http://localhost:8080/api/events/1/revenue`

### 12. Get Attendee Bookings
GET `http://localhost:8080/api/attendees/1/bookings`

### 13. Cancel Booking
PUT `http://localhost:8080/api/bookings/1/cancel`

### 14. Verify Inventory
GET `http://localhost:8080/api/events/1`

### 15. Verify Revenue
GET `http://localhost:8080/api/events/1/revenue`

## Screenshots

Save to `screenshots/` folder:
- `01-create-organizer.png`
- `02-create-venue.png`
- `03-create-event.png`
- `04-create-ticket-premium.png`
- `05-create-ticket-standard.png`
- `06-create-ticket-earlybird.png`
- `07-get-all-events.png`
- `08-get-event-by-id.png`
- `09-register-attendee.png`
- `10-create-booking.png`
- `11-get-revenue.png`
- `12-get-attendee-bookings.png`
- `13-cancel-booking.png`
- `14-verify-inventory.png`
- `15-verify-revenue-updated.png`

## Common Issues

**"role 'postgres' does not exist"**
Fix: Change username in `application.properties` to your system username

**"database 'ticketing_db' does not exist"**
Fix: Run `createdb ticketing_db`

**"Connection refused"**
Fix: Start PostgreSQL
- macOS: `brew services start postgresql@14`
- Windows: Check Services app
- Linux: `sudo systemctl start postgresql`

**"Port 8080 already in use"**
Fix: Stop other apps or change port in `application.properties`

**"Required request body is missing"**
Fix: In Postman, select Body → raw → JSON

**"Duplicate key value"**
Fix: Use different email (emails are unique)

## Submission

1. Add names/CWIDs to README.md
2. Record demo video (5-10 min)
3. Upload to YouTube (unlisted)
4. Add YouTube URL to README.md
5. Push to GitHub
