# Event Ticketing System

## Team Information

- Joshua Lopez - CWID: 885522771
- [Add Name] - CWID: [Add CWID]
- [Add Name] - CWID: [Add CWID]

## Project Overview

Backend API for event ticketing platform. Manages events, venues, organizers, tickets, attendees, and bookings.

## API Endpoints

### 1. Create Organizer
**POST** `/api/organizers`
```json
{
  "name": "Live Nation",
  "email": "contact@livenation.com",
  "phone": "310-555-1234"
}
```

### 2. Create Venue
**POST** `/api/venues`
```json
{
  "name": "Madison Square Garden",
  "address": "4 Timesquare Plaza",
  "city": "New York",
  "totalCapacity": 20000
}
```

### 3. Create Event
**POST** `/api/events`
```json
{
  "title": "Summer Pool Party Music Festival 2026",
  "description": "The biggest music festival of the year",
  "eventDate": "2026-07-25T18:00:00",
  "status": "UPCOMING",
  "organizerId": 1,
  "venueId": 1
}
```

### 4. Get All Events
**GET** `/api/events`

### 5. Get Event by ID
**GET** `/api/events/{id}`

### 6. Register Attendee
**POST** `/api/attendees`
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com"
}
```

### 7. Create Booking
**POST** `/api/bookings`
```json
{
  "attendeeId": 1,
  "ticketTypeId": 1
}
```

### 8. Cancel Booking
**PUT** `/api/bookings/{id}/cancel`

### 9. Get Event Revenue
**GET** `/api/events/{id}/revenue`

### 10. Get Attendee Bookings
**GET** `/api/attendees/{id}/bookings`

## Screenshots

See `screenshots/` folder.

## Demo Video

**YouTube URL**: [Add your unlisted YouTube video URL here]
