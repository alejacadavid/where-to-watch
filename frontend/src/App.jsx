import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'

function App() {
  const [query, setQuery] = useState('')
  const [movies, setMovies] = useState([])
  const [loading, setLoading] = useState(false)

  const searchMovies = async (e) => {
    e.preventDefault()
    if (!query) return
    setLoading(true)
    
    try {
      const res = await fetch(`http://localhost:8080/api/movies/search?query=${query}`)
      const data = await res.json()
      setMovies(data.results || [])
    } catch (error) {
      console.error("Error fetching movies:", error)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container">
      <header>
        <h1>🇨🇴 DondeLoVeo (Colombia)</h1>
        <p>Find out where to stream your favorite movies in Colombia.</p>
        
        <form onSubmit={searchMovies} className="search-form">
          <input 
            type="text" 
            placeholder="Search for a movie..." 
            value={query}
            onChange={(e) => setQuery(e.target.value)}
          />
          <button type="submit" disabled={loading}>
            {loading ? 'Searching...' : 'Search'}
          </button>
        </form>
      </header>

      <main className="movie-grid">
        {movies.map(movie => (
          <MovieCard key={movie.id} movie={movie} />
        ))}
      </main>

      <footer className="app-footer">
        <p>This product uses the TMDB API but is not endorsed or certified by TMDB.</p>
        <img 
          src="https://www.themoviedb.org/assets/2/v4/logos/v2/blue_short-8e7b30f73a4020692ccca9c88bafe5dcb6f8a62a4c6bc55cd9ba82bb2cd95f6c.svg" 
          alt="TMDB Logo" 
          className="tmdb-logo"
        />
      </footer>
    </div>
  )
}

function MovieCard({ movie }) {
  const [providers, setProviders] = useState(null)
  const [loadingProviders, setLoadingProviders] = useState(false)

  const loadProviders = async () => {
    if (providers) return // Already loaded
    
    setLoadingProviders(true)
    try {
      const res = await fetch(`http://localhost:8080/api/movies/${movie.id}/providers`)
      const data = await res.json()
      
      // We only care about the "CO" (Colombia) region flatrate providers
      const colombiaData = data.results?.CO || {}
      setProviders(colombiaData.flatrate || [])
    } catch (error) {
      console.error("Error fetching providers:", error)
      setProviders([])
    } finally {
      setLoadingProviders(false)
    }
  }

  useEffect(() => {
    loadProviders()
  }, [])

  return (
    <div className="movie-card">
      {movie.poster_path ? (
        <img 
          src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`} 
          alt={movie.title} 
          className="movie-poster"
        />
      ) : (
        <div className="movie-poster placeholder">No Poster</div>
      )}
      
      <div className="movie-info">
        <h3>{movie.title}</h3>
        <p className="release-date">{movie.release_date}</p>
        
        <div className="providers-section">
          <h4>Available on:</h4>
          {loadingProviders ? (
             <p className="loading-text">Checking platforms...</p>
          ) : providers !== null && providers.length > 0 ? (
            <div className="provider-logos">
              {providers.map(p => (
                <img 
                  key={p.provider_id}
                  title={p.provider_name}
                  src={`https://image.tmdb.org/t/p/original${p.logo_path}`} 
                  alt={p.provider_name}
                  className="provider-logo"
                />
              ))}
            </div>
          ) : providers !== null && providers.length === 0 ? (
            <p className="not-available">Not available to stream.</p>
          ) : null}
        </div>
      </div>
    </div>
  )
}

export default App
