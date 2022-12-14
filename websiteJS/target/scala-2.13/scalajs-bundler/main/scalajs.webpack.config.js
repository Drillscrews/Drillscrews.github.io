module.exports = {
  "entry": {
    "websitejs-opt": ["/home/jakew2/Projects/TorvaldPages/websiteJS/target/scala-2.13/scalajs-bundler/main/websitejs-opt-entrypoint.js"]
  },
  "output": {
    "path": "/home/jakew2/Projects/TorvaldPages/websiteJS/target/scala-2.13/scalajs-bundler/main",
    "filename": "[name]-library.js",
    "library": "ScalaJSBundlerLibrary",
    "libraryTarget": "var"
  },
  "mode": "production",
  "devtool": "source-map",
  "module": {
    "rules": [{
      "test": new RegExp("\\.js$"),
      "enforce": "pre",
      "use": ["source-map-loader"]
    }]
  }
}