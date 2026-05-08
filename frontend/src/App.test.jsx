import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import App from './App';
import { vi } from 'vitest';

// Mock the global fetch
global.fetch = vi.fn();

describe('App Component', () => {
  beforeEach(() => {
    fetch.mockClear();
  });

  test('renders header and search form correctly', () => {
    render(<App />);
    expect(screen.getByText('🇨🇴 DondeLoVeo (Colombia)')).toBeInTheDocument();
    expect(screen.getByPlaceholderText('Search for a movie...')).toBeInTheDocument();
    expect(screen.getByRole('button', { name: 'Search' })).toBeInTheDocument();
  });

  test('searches for a movie and displays results', async () => {
    // Mock the search response
    const mockMovies = [
      { id: 1, title: 'The Matrix', release_date: '1999-03-30', poster_path: '/matrix.jpg' }
    ];
    
    // Setup fetch to handle both /search and /providers endpoints
    fetch.mockImplementation((url) => {
      if (url.includes('/api/movies/search')) {
        return Promise.resolve({
          json: () => Promise.resolve({ results: mockMovies })
        });
      }
      if (url.includes('/api/movies/1/providers')) {
        return Promise.resolve({
          json: () => Promise.resolve({ results: { CO: { flatrate: [] } } })
        });
      }
      return Promise.reject(new Error('Unknown URL'));
    });

    render(<App />);
    
    const input = screen.getByPlaceholderText('Search for a movie...');
    const button = screen.getByRole('button', { name: 'Search' });

    fireEvent.change(input, { target: { value: 'Matrix' } });
    fireEvent.click(button);

    // Assert loading state
    expect(screen.getByRole('button', { name: 'Searching...' })).toBeInTheDocument();

    // Wait for movies to be displayed
    await waitFor(() => {
      expect(screen.getByText('The Matrix')).toBeInTheDocument();
      expect(screen.getByText('1999-03-30')).toBeInTheDocument();
    });
  });
});